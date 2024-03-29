package org.dni9.pom.pages;

import io.qameta.allure.Step;
import org.dni9.pom.base.BasePage;
import org.dni9.pom.objects.BillingInfo;
import org.dni9.pom.objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
  private final By firstNameField = By.id("billing_first_name");
  private final By lastNameField = By.id("billing_last_name");
  private final By addressLine1Field = By.id("billing_address_1");
  private final By cityField = By.id("billing_city");
  private final By postcodeField = By.id("billing_postcode");
  private final By emailField = By.id("billing_email");
  private final By placeOrderBtn = By.id("place_order");
  private final By orderSuccessNotice = By.cssSelector(".woocommerce-notice");

  private final By loginLink = By.className("showlogin");
  private final By usernameField = By.id("username");
  private final By passwordField = By.id("password");
  private final By loginBtn = By.name("login");
  private final By loadingOverlay = By.cssSelector(".blockUI.blockOverlay");
  private final By countryDropDown = By.id("select2-billing_country-container");
  private final By stateDropDown = By.id("select2-billing_state-container");
  private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

  private final By productName = By.cssSelector(".cart_item > .product-name");

  public CheckoutPage(WebDriver driver) {
    super(driver);
  }

  @Step("Load checkout page")
  public CheckoutPage load() {
    load("/checkout");
    return this;
  }

  public CheckoutPage enterFirstName(String firstName) {
    WebElement firstNameEl = driver.findElement(firstNameField);
    firstNameEl.clear();
    firstNameEl.sendKeys(firstName);
    return this;
  }

  public CheckoutPage enterLastName(String lastName) {
    WebElement lastNameEl = driver.findElement(lastNameField);
    lastNameEl.clear();
    lastNameEl.sendKeys(lastName);
    return this;
  }

  public CheckoutPage selectCountry(String country) {
    waitShort.until(ExpectedConditions.elementToBeClickable(countryDropDown)).click();
    waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + country + "']"))).click();
    return this;
  }

  public CheckoutPage enterAddressLine1(String addressLine) {
    WebElement addressEl = driver.findElement(addressLine1Field);
    addressEl.clear();
    addressEl.sendKeys(addressLine);
    return this;
  }

  public CheckoutPage enterCity(String city) {
    WebElement cityEl = driver.findElement(cityField);
    cityEl.clear();
    cityEl.sendKeys(city);
    return this;
  }

  public CheckoutPage selectState(String state) {
    waitShort.until(ExpectedConditions.elementToBeClickable(stateDropDown)).click();
    waitShort.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + state + "']"))).click();
    return this;
  }

  public CheckoutPage enterPostalCode(String postalCode) {
    WebElement postalCodeEl = driver.findElement(postcodeField);
    postalCodeEl.clear();
    postalCodeEl.sendKeys(postalCode);
    return this;
  }

  public CheckoutPage enterEmail(String email) {
    WebElement emailEl = driver.findElement(emailField);
    emailEl.clear();
    emailEl.sendKeys(email);
    return this;
  }

  @Step("Fill billing information")
  public CheckoutPage setBillingInfo(BillingInfo address) {
    return enterFirstName(address.getFirstName())
        .enterLastName(address.getLastName())
        .selectCountry(address.getCountry())
        .enterAddressLine1(address.getAddressLineOne())
        .enterCity(address.getCity())
        .selectState(address.getState())
        .enterPostalCode(address.getPostalCode())
        .enterEmail(address.getEmail());
  }

  @Step("Place order")
  public CheckoutPage placeOrder() {
    waitForElementsToDisappear(loadingOverlay, 10);
    driver.findElement(placeOrderBtn).click();
    return this;
  }

  public String getNotice() {
    return waitLong.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessNotice)).getText();
  }

  @Step("Open login form")
  public CheckoutPage openLoginForm() {
    waitLong.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    return this;
  }

  public CheckoutPage enterUsername(String username) {
    WebElement usernameEl = waitLong.until(ExpectedConditions.visibilityOf(driver.findElement(usernameField)));
    usernameEl.clear();
    usernameEl.sendKeys(username);
    return this;
  }

  public CheckoutPage enterPassword(String password) {
    WebElement passwordEl = driver.findElement(passwordField);
    passwordEl.clear();
    passwordEl.sendKeys(password);
    return this;
  }

  public CheckoutPage clickLoginButton() {
    driver.findElement(loginBtn).click();
    return this;
  }

  @Step("Login with username = {user.username}")
  public CheckoutPage login(User user) {
    return enterUsername(user.getUsername()).enterPassword(user.getPassword()).clickLoginButton();
  }

  @Step("Select direct bank transfer option")
  public CheckoutPage selectDirectBankTransfer() {
    WebElement radioBtn = waitShort.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
    if (!radioBtn.isSelected())
      radioBtn.click();
    return this;
  }

  public String getProductName() {
    return waitShort.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
  }
}
