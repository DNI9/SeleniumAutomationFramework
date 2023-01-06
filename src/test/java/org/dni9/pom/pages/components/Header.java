package org.dni9.pom.pages.components;

import org.dni9.pom.base.BasePage;
import org.dni9.pom.pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {
  private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

  public Header(WebDriver driver) {
    super(driver);
  }

  public StorePage openStorePageUsingMenu() {
    driver.findElement(storeMenuLink).click();
    return new StorePage(driver);
  }
}
