package org.dni9.pages;

import org.openqa.selenium.WebElement;

public abstract class BasePage {

  public abstract void navigateTo();

  protected void enterData(WebElement element, String data) {
    element.clear();
    element.sendKeys(data);
  }
}
