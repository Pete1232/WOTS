package com.qa.dummydata

import com.qa.entities.Product
import scala.util.Random
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder

/**
 * This object creates a set of DummyData arrays
 * @author pnewman
 */
object DummyData {
  val entries = 100
  val databaseCustomerOrder = new DummyBuilder[CustomerOrder](entries)
  val databaseEmployee = new DummyBuilder[Employee](entries)
  val databaseProduct = new DummyBuilder[Product](entries)
  val databasePurchaseOrder = new DummyBuilder[PurchaseOrder](entries)
  databaseCustomerOrder.buildEntityArray(0,new CustomerOrder)
  databaseEmployee.buildEntityArray(0,new Employee)
  databaseProduct.buildEntityArray(0,new Product)
  databasePurchaseOrder.buildEntityArray(0,new PurchaseOrder)
}