package com.phoenix.inventory;

public class InventoryStatus {

  private String productId;
  private Boolean exists;

  public InventoryStatus(String productId, Boolean exists) {
    this.productId = productId;
    this.exists = exists;
  }

  public String getProductId() {
    return productId;
  }

  public Boolean getExists() {
    return exists;
  }
}
