package org.dni9.tests.theinternet;

import org.dni9.base.TheInternetBaseTest;
import org.dni9.data.theinternet.LoginData;
import org.testng.annotations.Test;

public class LoginPageTest extends TheInternetBaseTest {

  @Test
  public void testLoginPage() {
    LoginData loginData = getData("login", LoginData.class);
    loginPage.navigateAndFill(loginData);
    String successMsg = securePage.getSuccessMsg();
    System.out.println(successMsg);
    securePage.logOut();
  }
}
