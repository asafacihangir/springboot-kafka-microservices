package com.phoenix.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "http://localhost:8082/api/inventory/")
public interface InventoryClient {

  @GetMapping(value = "/by-product")
  InventoryStatus getByProduct(@RequestParam("productId") String productId);
}
