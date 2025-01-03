package org.dni9.pages.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.dni9.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class ProfilePage extends BasePage {

  @FindBy(id = "navbarDropdown")
  private WebElement btnProfileDropDown;

  @FindBy(linkText = "Profile")
  private WebElement linkProfile;

  @FindBy(xpath = "//h1[starts-with(text(), 'Hello')]")
  private WebElement txtUserName;

  @Override
  public void navigateTo() {
    log.info("navigating to profile page");
    btnProfileDropDown.click();
    linkProfile.click();
  }

  public String getUserName() {
    return txtUserName.getText();
  }
}
