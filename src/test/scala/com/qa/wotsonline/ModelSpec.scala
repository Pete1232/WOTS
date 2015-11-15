package com.qa.wotsonline

import com.qa.GUI.Model
import com.qa.entities.CustomerOrderLine
import com.qa.wotsunit.UnitSpec

/**
 * @author pnewman
 */
class ModelSpec extends UnitSpec{
  
  def testPopulateOrder{
    "The populateOrder method" should "return an array of products" in{
      val orderLines = Array[CustomerOrderLine](new CustomerOrderLine(1,2,10),new CustomerOrderLine(2,3,20),new CustomerOrderLine(3,4,30))
      Model.populateOrder(orderLines).getClass.getSimpleName should be ("Product[]")
    }
    it should ""
  }
  
/*  def testGetCustomerOrders{
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
  testGetProductByOrderId*/
  
  testPopulateOrder
}