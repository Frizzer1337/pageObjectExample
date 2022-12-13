package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

import com.frizzer.pageobject.page.SearchPage;
import com.frizzer.pageobject.test.CommonTest;
import org.testng.annotations.Test;

public class BarrSearchTest extends CommonTest {

  @Test
  public void testBarrSearch() {
    driver.get("https://store.vaporesso.com/search");
    SearchPage searchPage = new SearchPage(driver);
    String text = "barr";
    searchPage.searchText(text);
    assertThat(searchPage.findVapeNames(), everyItem(containsString("barr")));


  }

}
