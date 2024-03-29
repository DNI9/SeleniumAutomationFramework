package org.dni9.pom.objects;

public class BillingInfo {
  private String firstName;
  private String lastName;
  private String addressLineOne;
  private String city;
  private String postalCode;
  private String email;

  private String country;
  private String state;

  public BillingInfo() {
  }

  public BillingInfo(
      String firstName,
      String lastName,
      String addressLineOne,
      String city,
      String postalCode,
      String email,
      String country,
      String state
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.addressLineOne = addressLineOne;
    this.city = city;
    this.postalCode = postalCode;
    this.email = email;
    this.country = country;
    this.state = state;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddressLineOne() {
    return addressLineOne;
  }

  public void setAddressLineOne(String addressLineOne) {
    this.addressLineOne = addressLineOne;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
