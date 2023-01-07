package org.dni9.pom.tests;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.dataproviders.ProductProvider;
import org.dni9.pom.objects.Product;
import org.dni9.pom.pages.CartPage;
import org.dni9.pom.pages.HomePage;
import org.dni9.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

  @Test(description = "Adds and validates products to cart from store page")
  public void addToCartFromStorePage() throws IOException {
    Product product = new Product(1215);
    CartPage cartPage = new StorePage(getDriver())
        .load()
        .getProductListing()
        .addToCart(product.getName())
        .openCartPage();

    Assert.assertEquals(cartPage.getProductName(), product.getName());
  }

  @Test(
      dataProvider = "getFeaturedProducts",
      dataProviderClass = ProductProvider.class,
      description = "Add and validates adding products to cart from featured products"
  )
  public void addFeaturedProductsToCart(Product product) {
    HomePage homePage = new HomePage(getDriver()).load();
    CartPage cartPage = homePage
        .getProductListing()
        .addToCart(product.getName())
        .openCartPage();
    Assert.assertEquals(cartPage.getProductName(), product.getName());
  }
}
