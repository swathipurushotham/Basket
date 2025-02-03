package org.adthena.model

case class Product(productName:String,price :Double)

object Product {

  object Products {
    val SOUP = "Soup"
    val BREAD = "Bread"
    val MILK = "Milk"
    val APPLES = "Apples"
  }

  private val availableProducts = Map(
   Products.SOUP-> Product(Products.SOUP, 0.65),
    Products.BREAD -> Product(Products.BREAD, 0.80),
    Products.MILK -> Product(Products.MILK, 1.30),
    Products.APPLES -> Product(Products.APPLES, 1.00)
  )

  def getTotalProductsCost(products: List[Product]): Double = products.map(_.price).sum

  def getProductCount(name: String, basket: List[Product]): Int = basket.count(_.productName.equalsIgnoreCase(name))

  def getProductsInBasket(basket: List[String]): List[Product] = basket.map(availableProducts(_))

}

