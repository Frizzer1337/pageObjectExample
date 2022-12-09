package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.test.CommonTest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class AlphabetZAFilterTest extends CommonTest {

  @Test
  public void testSortAscendingByAlphabetZA() {
    driver.get("https://store.vaporesso.com/collections/collections?sort_by=title-descending");
    ProductPage productPage = new ProductPage(driver);
    List<String> names = productPage.findVapes().stream().map(x -> x.getName().toLowerCase())
        .collect(Collectors.toList());
    assertThat(names, is(equalTo(names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))));


  }
}
