package com.phoenix.shipping;

import com.phoenix.shipping.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "E_SHIPPING")
public class Shipping extends BaseEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private String id;

  @Column(name = "ORDER_ID")
  private String orderId;

  @Column(name = "METHOD")
  private String method;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "SHIPPING_DATE")
  private String shippingDate;

  @Column(name = "EXPECTED_DELIVERY_DATE")
  private String expectedDeliveryDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getShippingDate() {
    return shippingDate;
  }

  public void setShippingDate(String shippingDate) {
    this.shippingDate = shippingDate;
  }

  public String getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }

  public void setExpectedDeliveryDate(String expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }
}
