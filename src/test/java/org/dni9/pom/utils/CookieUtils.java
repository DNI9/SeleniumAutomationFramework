package org.dni9.pom.utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.stream.Collectors;

public class CookieUtils {
  public List<Cookie> convertToSeleniumCookies(Cookies cookies) {
    return cookies.asList().stream()
        .map(cookie -> new Cookie(
            cookie.getName(),
            cookie.getValue(),
            cookie.getDomain(),
            cookie.getPath(),
            cookie.getExpiryDate(),
            cookie.isSecured(),
            cookie.isHttpOnly(),
            cookie.getSameSite()
        )).collect(Collectors.toList());
  }
}
