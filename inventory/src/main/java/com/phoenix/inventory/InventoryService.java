package com.phoenix.inventory;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class InventoryService {



  public InventoryStatus getInventory(String productId){
    Assert.hasText(productId, "Product id can not be blank or null.");

    final boolean exist = ThreadLocalRandom.current().nextBoolean();
    return new InventoryStatus(productId, exist);
  }



}

