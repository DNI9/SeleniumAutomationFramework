package org.dni9.pages.theinternet;

import org.dni9.base.FillablePage;
import org.dni9.data.theinternet.LoginData;
import org.dni9.utils.LogUtils;
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
    LogUtils.infoPassWithScreenshot("Navigating to login page");
    linkLogin.click();
  }

  @Override
  public void fill(LoginData loginData) {
    enterData(txtUsername, loginData.getUsername());
    enterData(txtPassword, loginData.getPassword());
    LogUtils.infoPassWithScreenshot("Filling login page");
    txtPassword.sendKeys(Keys.ENTER);
  }
}
