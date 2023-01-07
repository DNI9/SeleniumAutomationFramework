package org.dni9.pom.pages;

import io.qameta.allure.Step;
import org.dni9.pom.base.BasePage;
import org.dni9.pom.pages.components.Header;
import org.dni9.pom.pages.components.ProductListing;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
  private final Header header;
  private final ProductListing productListing;

  public HomePage(WebDriver driver) {
    super(driver);
    header = new Header(driver);
    productListing = new ProductListing(driver);
  }

  public Header getHeader() {
    return header;
  }

  @Step("Get featured product")
  public ProductListing getProductListing() {
    return productListing;
  }

  @Step("Load homepage")
  public HomePage load() {
    super.load("/");
    return this;
  }


}
