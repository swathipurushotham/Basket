package org.adthena.model

import org.adthena.model.Product.{Products}
import org.scalatest.FunSuite

class ProductTest extends FunSuite{

  val products =  List(Product(Products.SOUP,0.65),Product(Products.BREAD,0.80),Product(Products.MILK,1.30),Product(Products.APPLES,1.00))

  test("total cost of no items is 0") {
    val basket = List()
    val cost = Product.getTotalProductsCost(basket)
    assert(cost == 0.0)

  }
  test("calculate sum of all prices correctly") {
    val products =  List(Product(Products.SOUP,0.65),Product(Products.BREAD,0.80),Product(Products.MILK,1.30),Product(Products.APPLES,1.00))
    val cost = Product.getTotalProductsCost(products)
    assert(cost == 3.75)

  }

  test("count items in basket") {
    val basket = List()
    val soupCount = Product.getProductCount(Products.SOUP,products)
    val breadCount = Product.getProductCount(Products.BREAD,products)
    val milkCount = Product.getProductCount(Products.MILK,products)
    val appleCount = Product.getProductCount(Products.APPLES,products)
    assert(soupCount == 1)
    assert(breadCount == 1)
    assert(milkCount == 1)
    assert(appleCount == 1)

  }
}
