package com.qa.data

import com.qa.entities.Product
import scala.util.Random
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * This object creates a set of DummyData arrays
 * @author pnewman
 */
object DummyData {
  val logger = Logger(LoggerFactory.getLogger("DummyData.object"))
  val entries = 50
  val databaseCustomerOrder = new DummyBuilder[CustomerOrder](entries)
  val databaseEmployee = new DummyBuilder[Employee](entries)
  val databaseProduct = new DummyBuilder[Product](10*entries)
  val databasePurchaseOrder = new DummyBuilder[PurchaseOrder](entries)
  databaseCustomerOrder.buildEntityArray(0,new CustomerOrder)
  databaseEmployee.buildEntityArray(0,new Employee)
  databaseProduct.buildEntityArray(0,new Product)
  databasePurchaseOrder.buildEntityArray(0,new PurchaseOrder)
  logger.debug("Employee 6 username: "+databaseEmployee.databaseArray(5).employeeUsername_ +". Employee 6 password: "+databaseEmployee.databaseArray(5).employeePassword_)
}