package org.dni9.pom.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.dni9.pom.base.BaseTest;
import org.dni9.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Product search test")
public class SearchTest extends BaseTest {

  @Test(description = "Validates searching products with partial match")
  @Story("Search product with partial match")
  public void searchWithPartialMatch() {
    final String query = "blue";
    StorePage storePage = new StorePage(getDriver())
        .load()
        .searchProduct(query);

    Assert.assertEquals(storePage.getSearchTitle(), "Search results: “blue”");
  }
}
