package org.dni9.pom.pages;

import org.dni9.pom.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
  @FindBy(css = "td.product-name > a")
  @CacheLookup // NOTE: speeds up element look up without calling driver, can not be used for dynamic elements
  private WebElement productName;

  @FindBy(css = ".checkout-button")
  private WebElement checkoutBtn;

  public CartPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getProductName() {
    return waitLong.until(ExpectedConditions.visibilityOf(productName)).getText();
  }

  public CheckoutPage openCheckoutPage() {
    checkoutBtn.click();
    return new CheckoutPage(driver);
  }
}
