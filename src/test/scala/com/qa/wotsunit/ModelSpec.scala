package com.qa.wotsunit

import com.qa.GUI.Model
import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import scala.util.Random
import scalafx.collections.ObservableBuffer
import org.scalatest.Suite
import org.scalatest.Matchers
import org.scalatest.Inspectors
import org.scalatest.Inside
import org.scalatest.FlatSpec
import org.scalatest.Suites

/**
 * @author pnewman
 */
class ModelSpec extends UnitSpec{
  def testGetCustomerOrders{
    "The getCustomerOrders method" should "convert a given Array of CustomerOrder to an Observable buffer" in{
      val customerOrders = Array[CustomerOrder](new CustomerOrder,new CustomerOrder)
      Model.getCustomerOrders(customerOrders) should be (ObservableBuffer[CustomerOrder](customerOrders(0),customerOrders(1)))
    }
  }
  def testGetProductByOrderId{
    "The getProductByOrderId method" should "return a List of Product with given orderId" in{
      val products = Array[Product](new Product(1),new Product(1),new Product(2),new Product(77))
      Model.getProductByOrderId(1,products) should be (Array[Product](products(0),products(1)))
      Model.getProductByOrderId(2,products) should be (Array[Product](products(2)))
      Model.getProductByOrderId(77,products) should be (Array[Product](products(3)))
      Model.getProductByOrderId(12,products) should be (Array[Product]())
    }
  }
  testGetCustomerOrders
  testGetProductByOrderId
}