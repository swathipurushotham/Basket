package org.adthena.service

import org.adthena.model.Output
import org.adthena.model.Product.{getProductsInBasket, getTotalProductsCost}
import org.adthena.model.DiscountOffer.applyDiscountOffers

import scala.math.BigDecimal.RoundingMode
import scala.io.StdIn.readLine

object CheckOut {

  def main(args: Array[String]): Unit = {

      val basket = readLine()
      calculateTotal(basket.split(",").toList)
    }


  def formatCurrency(amount: Double): String = f"£$amount%.2f"

  def calculateTotal(basket: List[String]): Output = {

    val productsInBasket = getProductsInBasket(basket)

    val subtotal = getTotalProductsCost(productsInBasket)
    val offers = applyDiscountOffers(productsInBasket)
    val total = BigDecimal(subtotal - offers.map(_.discount).sum).setScale(2, RoundingMode.HALF_UP).toDouble

    println(s"Subtotal: ${formatCurrency(subtotal)}")

    if (offers.isEmpty) {
      println("(No offers available)")
    } else {
      offers.foreach(offer => println(s"${offer.description}: ${offer.discount}"))
    }
    println(s"Total price: ${formatCurrency(total)}")
    Output(subtotal, offers, total)

  }
}