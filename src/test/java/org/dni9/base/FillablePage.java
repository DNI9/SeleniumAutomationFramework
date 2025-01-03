package org.dni9.base;

import org.dni9.data.TestData;

public abstract class FillablePage<T extends TestData> extends BasePage {

  public abstract void fill(T testData);

  public void navigateAndFill(T testData) {
    navigateTo();
    fill(testData);
  }
}
