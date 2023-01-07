package org.dni9.pom.tests;

import org.dni9.pom.api.actions.CartApi;
import org.dni9.pom.api.actions.SignUpApi;
import org.dni9.pom.base.BaseTest;
import org.dni9.pom.objects.Product;
import org.dni9.pom.objects.User;
import org.dni9.pom.pages.CheckoutPage;
import org.dni9.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
  @Test(description = "Validates login during checkout")
  public void loginDuringCheckout() throws IOException {
    long randomNumber = new FakerUtils().generateRandomNumber();
    String username = "demouser" + randomNumber;
    String password = "demopwd" + randomNumber;
    String email = username + "@email.com";
    User user = new User(username, password, email);

    SignUpApi signUpApi = new SignUpApi();
    signUpApi.register(user);

    CartApi cartApi = new CartApi();
    Product product = new Product(1215);
    cartApi.addToCart(product.getId(), 1);

    CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load(); // will redirect to /cart page
    injectCookiesToBrowser(cartApi.getCookies()); // inject cookies after website has loaded, avoids domain error if injected before loading site
    checkoutPage.load(); // load / checkout page

    checkoutPage
        .openLoginForm()
        .login(user);

    Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
  }
}
