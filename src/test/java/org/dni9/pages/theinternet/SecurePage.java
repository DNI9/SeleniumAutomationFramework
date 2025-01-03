package org.dni9.pages.theinternet;

import lombok.extern.slf4j.Slf4j;
import org.dni9.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
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
    log.info("Logging out");
    linkLogout.click();
  }
}
