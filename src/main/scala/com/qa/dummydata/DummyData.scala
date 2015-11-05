package com.qa.dummydata

import com.qa.entities.Product
import scala.util.Random
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder

/**
 * @author pnewman
 */
object DummyData {
  val entries = 100
  val databaseCustomerOrder = new DummyBuilder[CustomerOrder](entries)
  val databaseEmployee = new DummyBuilder[Employee](entries)
  val databaseProduct = new DummyBuilder[Product](entries)
  val databasePurchaseOrder = new DummyBuilder[PurchaseOrder](entries)
  databaseCustomerOrder.buildEntityArray(0,entries,new CustomerOrder)
  databaseEmployee.buildEntityArray(0, entries, new Employee)
  databaseProduct.buildEntityArray(0,entries,new Product)
  databasePurchaseOrder.buildEntityArray(0,entries,new PurchaseOrder)
}