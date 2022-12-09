package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.annotations.Test;


public class CartAddSingleItemTest extends CommonTest {

  @Test
  public void testAddSingleItemToCart() {
    driver.get("https://store.vaporesso.com/collections/collections");
    ProductPage productPage = new ProductPage(driver);
    VaporessoProduct firstProduct = productPage.findVapes().get(0);
    driver.get(firstProduct.getLink());
    SingleProductPage singleProductPage = new SingleProductPage(driver).addToCart();
    driver.get("https://store.vaporesso.com/collections/collections");
    String itemName = new ProductPage(driver).openCart().getNameFirstItemInCart();
    assertThat(firstProduct.getName(), is(equalTo(itemName)));

  }


}
