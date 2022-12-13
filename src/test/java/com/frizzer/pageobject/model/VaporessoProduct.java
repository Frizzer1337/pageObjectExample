package com.frizzer.pageobject.model;

public class VaporessoProduct {

  private double currentPrice;
  private String name;
  private String link;

  public VaporessoProduct(double currentPrice, String name, String link) {
    this.currentPrice = currentPrice;
    this.name = name;
    this.link = link;
  }

  public double getCurrentPrice() {
    return currentPrice;
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

    if (Double.compare(that.currentPrice, currentPrice) != 0) {
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
    temp = Double.doubleToLongBits(currentPrice);
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + name.hashCode();
    result = 31 * result + link.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "VaporessoProduct{" +
        "currentPrice=" + currentPrice +
        ", name='" + name + '\'' +
        ", link='" + link + '\'' +
        '}';
  }


}
