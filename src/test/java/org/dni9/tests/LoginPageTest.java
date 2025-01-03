package org.dni9.tests;

import org.dni9.base.TheInternetBaseTest;
import org.dni9.data.LoginData;
import org.dni9.utils.JsonUtils;
import org.testng.annotations.Test;

public class LoginPageTest extends TheInternetBaseTest {

  @Test
  public void testLoginPage() {
    LoginData loginData = JsonUtils.readJson("login", LoginData.class);
    loginPage.navigateAndFill(loginData);
    String successMsg = securePage.getSuccessMsg();
    System.out.println(successMsg);
    securePage.logOut();
  }
}
