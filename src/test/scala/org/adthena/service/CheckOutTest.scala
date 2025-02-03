package org.adthena.service

import org.adthena.model.Product.Products
import org.scalamock.scalatest.MockFactory
import org.scalatest._


class CheckOutTest extends FunSuite  with  MockFactory {

    test("no items") {
      val basket = List()
      val out = CheckOut.calculateTotal(basket)
      assert(out.subtotal == 0.0)
      assert(out.discount.isEmpty)
      assert(out.totalPrice == 0.0)
    }

    test("read input from command line test") {
      val mockStdIn = mockFunction[String]
      mockStdIn.expects().returning("Apples,Apples")
      val basket = mockStdIn()
      val out = CheckOut.calculateTotal(basket.split(",").toList)
      assert(out.subtotal == 2.00)
      assert(out.totalPrice == 1.80)
    }

    test("no special offers") {
      val basket = List(Products.MILK)
      val out= CheckOut.calculateTotal(basket)
      assert(out.subtotal == 1.30)
      assert(out.discount.isEmpty)
      assert(out.totalPrice == 1.30)
    }

    test("apple discount") {
      val basket = List(Products.APPLES,Products.APPLES)
      val out= CheckOut.calculateTotal(basket)
      assert(out.subtotal == 2.00)
      assert(out.discount.head.discount == 0.20) // 10% discount on 2 apples
      assert(out.totalPrice == 1.80)
    }

    test("soup and bread offer") {
      val basket = List(Products.SOUP,Products.SOUP,Products.BREAD)
      val out= CheckOut.calculateTotal(basket)
      assert(out.subtotal == 2.10)
      assert(out.discount.head.discount == 0.40) // Half price for bread
      assert(out.totalPrice == 1.70)
    }

    test("combined offers") {
      val basket = List(Products.SOUP,Products.SOUP,Products.BREAD,Products.APPLES,Products.APPLES)
      val out= CheckOut.calculateTotal(basket)
      assert(out.subtotal == 4.10)
      assert(out.discount.head.discount == 0.20) // 10% off on apples
      assert(out.discount.last.discount ==0.40) // Half price on bread
      assert(out.totalPrice ==3.50)
    }
  }