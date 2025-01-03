package org.dni9.pages.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.dni9.base.FillablePage;
import org.dni9.data.bookstore.SignInData;
import org.dni9.utils.LogUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class SignInPage extends FillablePage<SignInData> {

  @FindBy(linkText = "Sign In")
  private WebElement linkSignIn;

  @FindBy(id = "email")
  private WebElement txtEmail;

  @FindBy(id = "password")
  private WebElement txtPassword;

  @Override
  public void navigateTo() {
    LogUtils.infoPassWithScreenshot("Navigating to sign in page");
    linkSignIn.click();
  }

  @Override
  public void fill(SignInData signInData) {
    enterData(txtEmail, signInData.getUsername());
    enterData(txtPassword, signInData.getPassword());
    LogUtils.infoPassWithScreenshot("Filling sign in page");
    txtPassword.sendKeys(Keys.ENTER);
  }

  public void signIn(SignInData signInData) {
    navigateAndFill(signInData);
  }
}
