package org.dni9.base;

import lombok.extern.slf4j.Slf4j;
import org.dni9.pages.bookstore.ProfilePage;
import org.dni9.pages.bookstore.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class BookStoreBaseTest extends BaseTest {

  protected static final String APP_NAME = "bookstore";

  protected SignInPage signInPage;
  protected ProfilePage profilePage;

  @Override
  protected void initializePageObjects(WebDriver driver) {
    signInPage = PageFactory.initElements(driver, SignInPage.class);
    profilePage = PageFactory.initElements(driver, ProfilePage.class);
  }

  @Override
  public String getAppName() {
    return APP_NAME;
  }
}
