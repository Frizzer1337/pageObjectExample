package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.test.CommonTest;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class AlphabetAZFilterTest extends CommonTest {

  @Test
  public void testSortAscendingByAlphabetAZ() {
    driver.get("https://store.vaporesso.com/collections/collections?sort_by=title-ascending");
    ProductPage productPage = new ProductPage(driver);
    List<String> names = productPage.findVapes().stream().map(x -> x.getName().toLowerCase())
        .filter(x -> !x.isEmpty())
        .collect(Collectors.toList());
    assertThat(names, is(equalTo(names.stream().sorted().collect(Collectors.toList()))));
  }


}
