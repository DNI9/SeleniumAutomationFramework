package org.dni9.pom.pages.components;

import org.dni9.pom.base.BasePage;
import org.dni9.pom.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductListing extends BasePage {
  private final By viewCartLink = By.cssSelector("a[title='View cart']");

  public ProductListing(WebDriver driver) {
    super(driver);
  }

  public ProductListing addToCart(String productName) {
    By addToCartBtn = By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    driver.findElement(addToCartBtn).click();
    return this;
  }

  public CartPage openCartPage() {
    waitLong.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    return new CartPage(driver);
  }
}
