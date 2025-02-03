package org.adthena.model

import org.adthena.model.Product.{Products, getProductCount}

case class DiscountOffer(description: String, discount: Double)

object DiscountOffer {

  private val availableDiscounts = Map(
    Products.APPLES-> DiscountOffer("Apples 10% off", 0.10)
  )

  def applyDiscountOffers(basket: List[Product]): List[DiscountOffer] = {
    val items = basket.distinct.collect(_.productName)
    val singleDiscount =items.flatMap(name => applySingleProductDiscount(name, basket))
    val soupBreadDiscount = applyBreadForSoupDiscount(basket)
    singleDiscount ++ soupBreadDiscount
  }

  def applySingleProductDiscount(name: String,basket: List[Product]): List[DiscountOffer] = {

    name match {
      case Products.APPLES =>
        val appleCount = getProductCount(name, basket)
        appleCount match {
          case c if c > 0 =>
            List(DiscountOffer(availableDiscounts(Products.APPLES).description,availableDiscounts(Products.APPLES).discount * appleCount))
          case _ =>
            List()
        }
      case _ =>
          List()
      }
    }


  def applyBreadForSoupDiscount(basket: List[Product]): List[DiscountOffer] = {
    val soupCount = getProductCount(Products.SOUP, basket)
    val breadCount = getProductCount(Products.BREAD, basket)
    val discount = Math.min(breadCount, soupCount / 2)
    discount match {
      case discount if discount>0 =>
        List(DiscountOffer("Buy 2 tins of soup and get a loaf of bread for half price", 0.40 * discount))
      case _ =>
        List()
    }
  }

}