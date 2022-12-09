package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.test.CommonTest;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class AscendingFilterTest extends CommonTest {

  @Test
  public void testSortAscendingByPrice() {
    driver.get("https://store.vaporesso.com/collections/collections?sort_by=price-ascending");
    ProductPage productPage = new ProductPage(driver);
    List<Double> prices = productPage.findVapes().stream().map(VaporessoProduct::getCurrentPrice)
        .collect(
            Collectors.toList());
    assertThat(prices,is(equalTo(prices.stream().sorted().collect(Collectors.toList()))));
  }


}
