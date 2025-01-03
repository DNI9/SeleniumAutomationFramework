package org.dni9.tests.bookstore;

import org.dni9.base.BookStoreBaseTest;
import org.dni9.data.bookstore.SignInData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BookStoreBaseTest {

  @Test
  public void testSignIn() {
    SignInData signInData = getData("signIn", SignInData.class);
    signInPage.signIn(signInData);
    String userName = profilePage.getUserName();
    Assert.assertEquals(userName, "Hello test123");
  }
}
