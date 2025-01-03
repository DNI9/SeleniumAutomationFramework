package org.dni9.data.theinternet;

import lombok.Data;
import org.dni9.data.TestData;

@Data
public class LoginData implements TestData {
  public String username;
  public String password;
}
