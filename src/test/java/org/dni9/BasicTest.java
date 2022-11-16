package org.dni9;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.objects.BillingInfo;
import org.dni9.pom.objects.Product;
import org.dni9.pom.pages.CartPage;
import org.dni9.pom.pages.HomePage;
import org.dni9.pom.pages.StorePage;
import org.dni9.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BasicTest extends BaseTest {
  @Test
  public void guestCheckoutUsingDirectBankTransfer() throws IOException {
    BillingInfo billingInfo = JacksonUtils.deserializeJson("billingAddress.json", BillingInfo.class);
    Product product = new Product(1215);

    StorePage storePage = new HomePage(driver).load()
        .openStorePageUsingMenu()
        .searchProduct("blue");

    String searchTitle = storePage.getSearchTitle();
    Assert.assertEquals(searchTitle, "Search results: “blue”");

    CartPage cartPage = storePage
        .addToCart(product.getName())
        .openCartPage();
    Assert.assertEquals(cartPage.getProductName(), product.getName());

    String notice = cartPage.openCheckoutPage()
//        .openLoginForm()
//        .enterUsername("demouser2")
//        .enterPassword("demopwd")
//        .clickLoginButton()
        .setBillingInfo(billingInfo)
        .selectDirectBankTransfer()
        .placeOrder()
        .getNotice();

    Assert.assertEquals(notice, "Thank you. Your order has been received.");
  }
}
