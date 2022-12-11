package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class CartQuantityTest extends CommonTest {

  @Test
  public void testCartQuantity() {
    driver.get("https://store.vaporesso.com/products/xros-3");
    int amountToAdd = 3;
    String vapeQuantity = new SingleProductPage(driver).addToCart(amountToAdd).quantityOfFirstInCart();
    assertThat(amountToAdd + "", is(equalTo(vapeQuantity)));


  }

}
