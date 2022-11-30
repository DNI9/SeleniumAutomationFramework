package org.dni9.pom.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
  public long generateRandomNumber() {
    Faker faker = new Faker();
    return faker.number().randomNumber(5, true);
  }
}
