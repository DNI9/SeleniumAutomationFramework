package org.dni9.pom.pages;

import org.dni9.pom.base.BasePage;
import org.dni9.pom.pages.components.ProductListing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class StorePage extends BasePage {
  private final By searchField = By.id("woocommerce-product-search-field-0");
  private final By searchBtn = By.cssSelector("button[value='Search']");
  private final By searchTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
  private final ProductListing productListing;

  public StorePage(WebDriver driver) {
    super(driver);
    productListing = new ProductListing(driver);
  }

  public ProductListing getProductListing() {
    return productListing;
  }

  public StorePage load() {
    load("/store");
    return this;
  }

  public StorePage searchProduct(String query) {
    waitLong.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(query);
    driver.findElement(searchBtn).click();
    return this;
  }

  public String getSearchTitle() {
    // NOTE: this can be done by checking the url too
    waitShort.until(ExpectedConditions.textMatches(searchTitle, Pattern.compile("Search results", Pattern.CASE_INSENSITIVE)));
    return driver.findElement(searchTitle).getText();
  }

  public boolean doesUrlContains(String key) {
    return driver.getCurrentUrl().contains(BASE_URL + key);
  }
}
