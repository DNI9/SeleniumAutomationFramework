package org.dni9.pages;

import lombok.extern.slf4j.Slf4j;
import org.dni9.base.FillablePage;
import org.dni9.data.LoginData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class LoginPage extends FillablePage<LoginData> {

  @FindBy(linkText = "Form Authentication")
  private WebElement linkLogin;

  @FindBy(name = "username")
  private WebElement txtUsername;

  @FindBy(name = "password")
  private WebElement txtPassword;

  @Override
  public void navigateTo() {
    log.info("Navigating to login page");
    linkLogin.click();
  }

  @Override
  public void fill(LoginData loginData) {
    log.info("Filling login page");
    enterData(txtUsername, loginData.getUsername());
    enterData(txtPassword, loginData.getPassword());
    txtPassword.sendKeys(Keys.ENTER);
  }
}
