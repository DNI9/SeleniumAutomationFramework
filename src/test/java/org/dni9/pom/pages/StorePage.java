package org.dni9.pom.pages;

import org.dni9.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class StorePage extends BasePage {
  private final By searchField = By.id("woocommerce-product-search-field-0");
  private final By searchBtn = By.cssSelector("button[value='Search']");
  private final By searchTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
  private final By viewCartLink = By.cssSelector("a[title='View cart']");

  public StorePage(WebDriver driver) {
    super(driver);
  }

  public StorePage searchProduct(String query) {
    waitLong.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(query);
    driver.findElement(searchBtn).click();
    return this;
  }

  public String getSearchTitle() {
    waitShort.until(ExpectedConditions.textMatches(searchTitle, Pattern.compile("Search results", Pattern.CASE_INSENSITIVE)));
    return driver.findElement(searchTitle).getText();
  }

  public StorePage addToCart(String productName) {
    By addToCartBtn = By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    driver.findElement(addToCartBtn).click();
    return this;
  }

  public CartPage openCartPage() {
    waitLong.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
    return new CartPage(driver);
  }
}
