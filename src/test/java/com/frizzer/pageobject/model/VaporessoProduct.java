package com.frizzer.pageobject.model;

import java.util.Objects;

public class VaporessoProduct {
  private double regularPrice;
  private double salePrice = -1;
  private String name;
  private String link;

  public VaporessoProduct(double regularPrice, double salePrice, String name, String link) {
    this.regularPrice = regularPrice;
    this.salePrice = salePrice;
    this.name = name;
    this.link = link;
  }

  public double getPrice(){
    return salePrice > 0 ? salePrice : regularPrice;
  }

  public double getRegularPrice() {
    return regularPrice;
  }

  public void setRegularPrice(double regularPrice) {
    this.regularPrice = regularPrice;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VaporessoProduct that = (VaporessoProduct) o;

    if (Double.compare(that.regularPrice, regularPrice) != 0) {
      return false;
    }
    if (Double.compare(that.salePrice, salePrice) != 0) {
      return false;
    }
    if (!name.equals(that.name)) {
      return false;
    }
    return link.equals(that.link);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(regularPrice);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(salePrice);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + name.hashCode();
    result = 31 * result + link.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "VaporessoProduct{" +
        "regularPrice=" + regularPrice +
        ", salePrice=" + salePrice +
        ", name='" + name + '\'' +
        ", link='" + link + '\'' +
        '}';
  }


}
