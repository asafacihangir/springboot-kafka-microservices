package com.phoenix.shipping;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final ShippingRepository shippingRepository;
  private final Faker faker;

  private final KafkaTemplate kafkaTemplate;

  public OrderService(
      ShippingRepository shippingRepository, Faker faker, KafkaTemplate kafkaTemplate) {
    this.shippingRepository = shippingRepository;
    this.faker = faker;
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(topics = "prod.order.placed", groupId = "shipping-group")
  public void handleOrderPlacedEvent(OrderPlacedEvent event) {
    final Date expectedDeliveryDate = faker.date().future(12, TimeUnit.DAYS);
    final Shipping shipping = new Shipping();
    shipping.setOrderId(event.getOrderId());
    shipping.setAddress(faker.address().fullAddress());
    shipping.setMethod("KARA");
    shipping.setShippingDate(LocalDate.now().toString());
    shipping.setExpectedDeliveryDate(
        expectedDeliveryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());

    final Shipping savedShipping = shippingRepository.save(shipping);

    this.kafkaTemplate.send(
        "prod.order.shipped",
        savedShipping.getId(),
        new OrderPlacedEvent(event.getOrderId(), event.getProduct(), event.getPrice()));
  }
}
