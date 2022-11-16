package org.dni9.pom.objects;

import org.dni9.pom.utils.JacksonUtils;

import java.io.IOException;
import java.util.Arrays;

public class Product {
  private int id;
  private String name;

  public Product() {
  }

  public Product(int id) throws IOException {
    this.id = id;
    Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
    Arrays.stream(products).filter(product -> product.getId() == id)
        .findFirst()
        .ifPresentOrElse(product -> this.name = product.getName(), () -> {
          throw new IllegalArgumentException("Product with id " + id + " not found.");
        });
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
