package org.dni9.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.asynchttpclient.util.HttpConstants;
import org.dni9.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {
  private Cookies cookies;

  public CartApi() {
  }

  public CartApi(Cookies cookies) {
    this.cookies = cookies;
  }

  public Cookies getCookies() {
    return cookies;
  }

  public Response addToCart(int productId, int quantity) {
    Header typeHeader = new Header("content-type", "application/x-www-form-urlencoded");
    Headers headers = new Headers(typeHeader);

    HashMap<String, Object> formParams = new HashMap<>();
    formParams.put("product_sku", "");
    formParams.put("product_id", productId);
    formParams.put("quantity", quantity);

    if (cookies == null) cookies = new Cookies();

    Response response = given().
        baseUri(ConfigLoader.getInstance().getConfig("baseUrl"))
        .headers(headers)
        .formParams(formParams)
        .cookies(cookies)
        .log().all()
        .when()
        .post("?wc-ajax=add_to_cart")
        .then()
        .log().all()
        .extract()
        .response();

    if (response.getStatusCode() != HttpConstants.ResponseStatusCodes.OK_200) {
      throw new RuntimeException("Failed add product to cart, productId = " + productId + ", Status code: " + response.getStatusCode());
    }

    this.cookies = response.getDetailedCookies();
    return response;
  }
}
