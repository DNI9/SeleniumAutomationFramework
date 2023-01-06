package org.dni9.pom.pages;

import org.dni9.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
  private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
  private final By viewCartLink = By.cssSelector("a[title='View cart']");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public HomePage load() {
    super.load("/");
    return this;
  }

  public StorePage openStorePageUsingMenu() {
    driver.findElement(storeMenuLink).click();
    return new StorePage(driver);
  }

  public HomePage addToCart(String productName) {
    By addToCartBtn = By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    driver.findElement(addToCartBtn).click();
    return this;
  }

  public CartPage openCartPage() {
    waitLong.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    return new CartPage(driver);
  }
}
