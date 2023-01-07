package org.dni9.pom.tests;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.pages.HomePage;
import org.dni9.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

  @Test(description = "Validates navigation using main menu to store page")
  public void navigateFromHomeToStoreUsingMainMenu() {
    StorePage storePage = new HomePage(getDriver())
        .load()
        .getHeader()
        .openStorePageUsingMenu();
    Assert.assertTrue(storePage.doesUrlContains("/store"));
  }
}
