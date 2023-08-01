package com.phoenix.order;

import java.math.BigDecimal;

public class OrderPlacedEvent {

  private String orderId;
  private String product;
  private BigDecimal price;

  public OrderPlacedEvent() {}

  public OrderPlacedEvent(String orderId, String product, BigDecimal price) {
    this.orderId = orderId;
    this.product = product;
    this.price = price;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getProduct() {
    return product;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
