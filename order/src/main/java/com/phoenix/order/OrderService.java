package com.phoenix.order;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

  private final KafkaTemplate kafkaTemplate;
  private final OrderRepository orderRepository;

  private final Faker faker;
  private final InventoryClient inventoryClient;

  private final TransactionTemplate transactionTemplate;

  public OrderService(
      KafkaTemplate kafkaTemplate,
      OrderRepository orderRepository,
      Faker faker,
      InventoryClient inventoryClient,
      TransactionTemplate transactionTemplate) {
    this.kafkaTemplate = kafkaTemplate;
    this.orderRepository = orderRepository;
    this.faker = faker;
    this.inventoryClient = inventoryClient;
    this.transactionTemplate = transactionTemplate;
  }

  public void placeOrder() {
    final PlaceOrderRequest placeOrderRequest = createPlaceOrderRequest();

    controlProductInventoryStatus();

    final Order order = new Order();
    order.setPrice(placeOrderRequest.getPrice());
    order.setProduct(placeOrderRequest.getProduct());
    order.setOrderStatus(OrderStatus.CREATED.toString());

    final Order savedOrder = orderRepository.saveAndFlush(order);

    this.kafkaTemplate.send(
        "prod.order.placed",
        savedOrder.getId(),
        new OrderPlacedEvent(savedOrder.getId(), savedOrder.getProduct(), savedOrder.getPrice()));
  }

  @KafkaListener(topics = "prod.order.shipped", groupId = "shipping-group")
  public void handleOrderShippedEvent(OrderPlacedEvent event) {
    transactionTemplate.executeWithoutResult(
        (status -> {
          this.orderRepository
              .findById(event.getOrderId())
              .ifPresent(
                  order -> {
                    order.setOrderStatus(OrderStatus.SHIPPED.toString());
                    this.orderRepository.save(order);
                  });
        }));
  }

  private void controlProductInventoryStatus() {
    String productId = UUID.randomUUID().toString();
    final InventoryStatus inventoryStatus = inventoryClient.getByProduct(productId);
    if (Boolean.TRUE.equals(inventoryStatus.getExists())) {
      throw new EntityNotFoundException(
          String.format("Product with id=%s does not exists", productId));
    }
  }

  private PlaceOrderRequest createPlaceOrderRequest() {
    final String productName = faker.commerce().productName();
    final BigDecimal productPrice = new BigDecimal(faker.commerce().price());
    return new PlaceOrderRequest(productName, productPrice);
  }
}
