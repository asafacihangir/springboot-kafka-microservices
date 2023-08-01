package com.phoenix.order;

import java.math.BigDecimal;

public class PlaceOrderRequest {

  private final String product;
  private final BigDecimal price;


  public PlaceOrderRequest(String product, BigDecimal price) {
    this.product = product;
    this.price = price;
  }

  public String getProduct() {
    return product;
  }

  public BigDecimal getPrice() {
    return price;
  }
}

