package org.dni9.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.asynchttpclient.util.HttpConstants;
import org.dni9.pom.objects.User;
import org.dni9.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
  private Cookies cookies;

  public Cookies getCookies() {
    return cookies;
  }

  private Response getAccount() {
    Response response = given().
        baseUri(ConfigLoader.getInstance().getConfig("baseUrl"))
        .log().all()
        .when()
        .get("/account")
        .then()
        .log().all()
        .extract()
        .response();

    if (response.getStatusCode() != HttpConstants.ResponseStatusCodes.OK_200) {
      throw new RuntimeException("Failed to fetch the account, Status code: " + response.getStatusCode());
    }

    return response;
  }

  public Response register(User user) {
    Cookies cookies = new Cookies();
    Header typeHeader = new Header("content-type", "application/x-www-form-urlencoded");
    Headers headers = new Headers(typeHeader);

    HashMap<String, String> formParams = new HashMap<>();
    formParams.put("username", user.getUsername());
    formParams.put("email", user.getEmail());
    formParams.put("password", user.getPassword());
    formParams.put("woocommerce-register-nonce", getRegisterNonceValue());
    formParams.put("register", "Register");

    Response response = given().
        baseUri(ConfigLoader.getInstance().getConfig("baseUrl"))
        .headers(headers)
        .formParams(formParams)
        .cookies(cookies)
        .log().all()
        .when()
        .post("/account")
        .then()
        .log().all()
        .extract()
        .response();

    if (response.getStatusCode() != HttpConstants.ResponseStatusCodes.FOUND_302) {
      throw new RuntimeException("Failed to register user, Status code: " + response.getStatusCode() + ", User: " + user);
    }

    this.cookies = response.getDetailedCookies();
    return response;
  }

  public String getRegisterNonceValue() {
    Response response = getAccount();

//    Jsoup way
//    Document document = Jsoup.parse(response.body().asString());
//    Element nonceEl = document.selectFirst("#woocommerce-register-nonce");
//    return nonceEl.val();

    // Groovy way
    return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
  }
}
