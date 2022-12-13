package com.frizzer.pageobject.test.impl;

import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTest extends CommonTest {

  @Test
  public void testCartByAddingMultipleElements() {
    driver.get("https://store.vaporesso.com/collections/collections");
    for (int i = 0; i < 2; i++) {
      ProductPage productPage = new ProductPage(driver);
      driver.get(productPage.findVapes().get(i).getLink());
      SingleProductPage singleProductPage = new SingleProductPage(driver).addToCart();
      driver.get("https://store.vaporesso.com/collections/collections");
    }
    driver.get("https://store.vaporesso.com/collections/collections");
    ProductPage productPage = new ProductPage(driver).openCart();
    Assert.assertEquals(productPage.countItemsInCart(), 2);

  }


}
