package org.dni9.pom.tests;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

  @Test
  public void searchWithPartialMatch() {
    final String query = "blue";
    StorePage storePage = new StorePage(getDriver())
        .load()
        .searchProduct(query);

    Assert.assertEquals(storePage.getSearchTitle(), "Search results: “blue”");
  }
}
