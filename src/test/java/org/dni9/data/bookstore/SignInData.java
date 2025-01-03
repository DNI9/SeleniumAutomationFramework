package org.dni9.data.bookstore;

import lombok.Data;
import org.dni9.data.TestData;

@Data
public class SignInData implements TestData {
  public String username;
  public String password;
}
