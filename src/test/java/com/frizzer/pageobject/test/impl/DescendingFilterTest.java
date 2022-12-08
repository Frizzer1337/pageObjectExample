package com.frizzer.pageobject.test.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.test.CommonTest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.Test;

public class DescendingFilterTest extends CommonTest{

    @Test
    public void testSortDescendingByPrice() {
      driver.get("https://store.vaporesso.com/collections/collections?sort_by=price-descending");
      ProductPage productPage = new ProductPage(driver);
      List<Double> prices = productPage.findVapes().stream().map(VaporessoProduct::getCurrentPrice)
          .filter(x->x>0)
          .collect(
              Collectors.toList());
      assertThat(prices,is(equalTo(prices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))));
    }



}
