package org.dni9.pom.tests;

import org.dni9.pom.base.BaseTest;
import org.dni9.pom.objects.BillingInfo;
import org.dni9.pom.objects.Product;
import org.dni9.pom.objects.User;
import org.dni9.pom.pages.CartPage;
import org.dni9.pom.pages.HomePage;
import org.dni9.pom.pages.StorePage;
import org.dni9.pom.utils.ConfigLoader;
import org.dni9.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BasicTest extends BaseTest {
  @Test
  public void guestCheckoutUsingDirectBankTransfer() throws IOException {
    BillingInfo billingInfo = JacksonUtils.deserializeJson("billingAddress.json", BillingInfo.class);
    Product product = new Product(1215);
    final String searchKey = "blue";

    StorePage storePage = new HomePage(getDriver()).load()
        .openStorePageUsingMenu()
        .searchProduct(searchKey);

    String searchTitle = storePage.getSearchTitle();
    Assert.assertEquals(searchTitle, "Search results: “blue”");

    CartPage cartPage = storePage
        .addToCart(product.getName())
        .openCartPage();
    Assert.assertEquals(cartPage.getProductName(), product.getName());

    String notice = cartPage.openCheckoutPage()
        .setBillingInfo(billingInfo)
        .selectDirectBankTransfer()
        .placeOrder()
        .getNotice();

    Assert.assertEquals(notice, "Thank you. Your order has been received.");
  }

  @Test
  public void guestCheckoutUsingDirectBankTransferWithLogin() throws IOException {
    BillingInfo billingInfo = JacksonUtils.deserializeJson("billingAddress.json", BillingInfo.class);
    Product product = new Product(1215);
    User user = new User(
        ConfigLoader.getInstance().getConfig("username"),
        ConfigLoader.getInstance().getConfig("password"));
    final String searchKey = "blue";

    StorePage storePage = new HomePage(getDriver()).load()
        .openStorePageUsingMenu()
        .searchProduct(searchKey);

    String searchTitle = storePage.getSearchTitle();
    Assert.assertEquals(searchTitle, "Search results: “blue”");

    CartPage cartPage = storePage
        .addToCart(product.getName())
        .openCartPage();
    Assert.assertEquals(cartPage.getProductName(), product.getName());

    String notice = cartPage.openCheckoutPage()
        .openLoginForm()
        .enterUsername(user.getUsername())
        .enterPassword(user.getPassword())
        .clickLoginButton()
        .setBillingInfo(billingInfo)
        .selectDirectBankTransfer()
        .placeOrder()
        .getNotice();

    Assert.assertEquals(notice, "Thank you. Your order has been received.");
  }
}
