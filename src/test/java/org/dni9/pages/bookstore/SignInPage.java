package org.dni9.pages.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.dni9.base.FillablePage;
import org.dni9.data.bookstore.SignInData;
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
    log.info("Navigating to sign in page");
    linkSignIn.click();
  }

  @Override
  public void fill(SignInData signInData) {
    log.info("Filling sign in page");
    enterData(txtEmail, signInData.getUsername());
    enterData(txtPassword, signInData.getPassword());
    txtPassword.sendKeys(Keys.ENTER);
  }

  public void signIn(SignInData signInData) {
    navigateAndFill(signInData);
  }
}
