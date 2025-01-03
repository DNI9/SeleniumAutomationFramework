package org.dni9.base;

import org.openqa.selenium.WebElement;

public abstract class BasePage {

  public abstract void navigateTo();

  protected void enterData(WebElement element, String data) {
    element.clear();
    element.sendKeys(data);
  }
}
