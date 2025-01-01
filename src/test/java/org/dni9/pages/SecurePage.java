package org.dni9.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecurePage extends BasePage {

  @FindBy(id = "flash")
  private WebElement txtSuccessMsg;

  @FindBy(xpath = "//a[contains(@href, 'logout')]")
  private WebElement linkLogout;

  @Override
  public void navigateTo() {
  }

  public String getSuccessMsg() {
    return txtSuccessMsg.getText();
  }

  public void logOut() {
    linkLogout.click();
  }
}
