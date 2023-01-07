package org.dni9.pom.dataproviders;

import org.dni9.pom.objects.Product;
import org.dni9.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ProductProvider {
  @DataProvider(name = "getFeaturedProducts", parallel = true)
  public static Object[][] getFeaturedProducts() throws IOException {
    Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
    var finalArr = new Object[products.length][];

    for (int i = 0; i < products.length; i++) {
      finalArr[i] = new Product[]{products[i]};
    }

    return finalArr;
  }
}
