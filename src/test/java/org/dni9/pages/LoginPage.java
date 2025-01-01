package org.dni9.pages;

import org.dni9.data.LoginData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends FillablePage<LoginData> {

  @FindBy(linkText = "Form Authentication")
  private WebElement linkLogin;

  @FindBy(name = "username")
  private WebElement txtUsername;

  @FindBy(name = "password")
  private WebElement txtPassword;

  @Override
  public void navigateTo() {
    linkLogin.click();
  }

  @Override
  public void fill(LoginData loginData) {
    enterData(txtUsername, loginData.getUsername());
    enterData(txtPassword, loginData.getPassword());
    txtPassword.sendKeys(Keys.ENTER);
  }
}
