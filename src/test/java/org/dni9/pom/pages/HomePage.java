package org.dni9.pom.pages;

import org.dni9.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
  private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

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
}
