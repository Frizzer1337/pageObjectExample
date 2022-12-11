package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.annotations.Test;

public class CartDeleteSingleItemTest extends CommonTest {

  @Test
  public void testDeleteSingleItemFromCart() {
    driver.get("https://store.vaporesso.com/collections/collections");
    ProductPage productPage = new ProductPage(driver);
    VaporessoProduct firstProduct = productPage.findVape();
    driver.get(firstProduct.getLink());
    String emptyCartText = new SingleProductPage(driver).addToCart().deleteFromCart().checkEmptyCart();
    assertThat(emptyCartText,equalTo("Your cart is empty"));


  }

}
