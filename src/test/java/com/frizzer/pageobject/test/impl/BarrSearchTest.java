package com.frizzer.pageobject.test.impl;

import static io.netty.util.internal.SystemPropertyUtil.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SearchPage;
import com.frizzer.pageobject.page.SingleProductPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.annotations.Test;

public class BarrSearchTest extends CommonTest {

  @Test
  public void testBarrSearch() {
    driver.get("https://store.vaporesso.com/search");
    SearchPage searchPage = new SearchPage(driver);
    String text = "barr";
    searchPage.searchText(text);
    assertThat(searchPage.findVapeNames(),everyItem(containsString("barr")));




  }

}
