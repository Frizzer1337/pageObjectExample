package com.frizzer.pageobject.test.impl;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartAddSingleItem extends CommonTest {

  @Test
  public void testAddSingleItemToCart() {
    driver.get("https://store.vaporesso.com/collections/collections");
    ProductPage productPage = new ProductPage(driver);
    VaporessoProduct firstProduct = productPage.findVapes().get(0);
    driver.get(firstProduct.getLink());
    SingleProductPage singleProductPage = new SingleProductPage(driver).addToCart();
    driver.get("https://store.vaporesso.com/collections/collections");
    Assert.assertEquals(firstProduct.getName(),"XROS 3");

  }


}
