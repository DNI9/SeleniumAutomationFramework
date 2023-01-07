package org.dni9.pom.tests;

import org.dni9.pom.api.actions.CartApi;
import org.dni9.pom.api.actions.SignUpApi;
import org.dni9.pom.base.BaseTest;
import org.dni9.pom.objects.BillingInfo;
import org.dni9.pom.objects.Product;
import org.dni9.pom.objects.User;
import org.dni9.pom.pages.CheckoutPage;
import org.dni9.pom.utils.FakerUtils;
import org.dni9.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

  @Test(description = "Validates checkout using guest account and direct bank transfer")
  public void guestCheckoutUsingDirectBankTransfer() throws IOException {
    BillingInfo billingInfo = JacksonUtils.deserializeJson("billingAddress.json", BillingInfo.class);
    CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

    CartApi cartApi = new CartApi();
    cartApi.addToCart(1215, 1);
    injectCookiesToBrowser(cartApi.getCookies());

    checkoutPage
        .load()
        .setBillingInfo(billingInfo)
        .selectDirectBankTransfer()
        .placeOrder();

    Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
  }

  @Test(description = "Validates checkout using user account and direct bank transfer")
  public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
    BillingInfo billingInfo = JacksonUtils.deserializeJson("billingAddress.json", BillingInfo.class);
    long randomNumber = new FakerUtils().generateRandomNumber();
    String username = "demouser" + randomNumber;
    String password = "demopwd" + randomNumber;
    String email = username + "@email.com";
    User user = new User(username, password, email);

    SignUpApi signUpApi = new SignUpApi();
    signUpApi.register(user);

    CartApi cartApi = new CartApi(signUpApi.getCookies());
    Product product = new Product(1215);
    cartApi.addToCart(product.getId(), 1);

    CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
    injectCookiesToBrowser(signUpApi.getCookies());
    checkoutPage
        .load()
        .setBillingInfo(billingInfo)
        .selectDirectBankTransfer()
        .placeOrder();

    Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
  }
}
