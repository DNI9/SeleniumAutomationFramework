package org.dni9.pom.tests;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.objects.Product;
import org.dni9.pom.pages.CartPage;
import org.dni9.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
  @Test
  public void addToCartFromStorePage() throws IOException {
    Product product = new Product(1215);
    CartPage cartPage = new StorePage(getDriver()).load()
        .addToCart(product.getName())
        .openCartPage();

    Assert.assertEquals(cartPage.getProductName(), product.getName());
  }
}
