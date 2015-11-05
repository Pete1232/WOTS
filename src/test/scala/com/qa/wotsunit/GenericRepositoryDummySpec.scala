package com.qa.wotsunit

import org.scalatest._
import com.qa.repositoryimplementations.GenericRepositoryDummy
import com.qa.entities.Product
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.PurchaseOrder
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

class GenericRepositoryDummySpec extends UnitSpec{
    val logger = Logger(LoggerFactory.getLogger("DummyBuilder.class"))
    val customerOrder = new CustomerOrder
    val employee = new Employee
    val product = new Product
    val purchaseOrder = new PurchaseOrder
    val grdCO = new GenericRepositoryDummy[CustomerOrder]
    val grdE = new GenericRepositoryDummy[Employee]
    val grdP = new GenericRepositoryDummy[Product]
    val grdPO = new GenericRepositoryDummy[PurchaseOrder]
  //TODO Add more methods as they are made
  "Calling findAll returns a function that" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = grdCO.GenericRepositoryDummy.findAll(customerOrder)
    val arrayE:Array[Employee] = grdE.GenericRepositoryDummy.findAll(employee)
    val arrayP:Array[Product] = grdP.GenericRepositoryDummy.findAll(product)
    val arrayPO:Array[PurchaseOrder] = grdPO.GenericRepositoryDummy.findAll(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
  "Calling persist returns a function that" should "append a single entity to the end of an array" in {
    val initialCO = grdCO.GenericRepositoryDummy.findAll(customerOrder).length
    val initialE = grdE.GenericRepositoryDummy.findAll(employee).length
    val initialP = grdP.GenericRepositoryDummy.findAll(product).length
    val initialPO = grdPO.GenericRepositoryDummy.findAll(purchaseOrder).length
    grdCO.GenericRepositoryDummy.persist(customerOrder)
    grdE.GenericRepositoryDummy.persist(employee)
    grdP.GenericRepositoryDummy.persist(product)
    grdPO.GenericRepositoryDummy.persist(purchaseOrder)
    val finalCO = grdCO.GenericRepositoryDummy.findAll(customerOrder).length
    val finalE = grdE.GenericRepositoryDummy.findAll(employee).length
    val finalP = grdP.GenericRepositoryDummy.findAll(product).length
    val finalPO = grdPO.GenericRepositoryDummy.findAll(purchaseOrder).length
    finalCO should be (initialCO+1)
    finalE should be (initialE+1)
    finalP should be (initialP+1)
    finalPO should be (initialPO+1)
    grdCO.GenericRepositoryDummy.findAll(customerOrder)(finalCO-1).customerOrderStatus should be (customerOrder.customerOrderStatus)
    grdE.GenericRepositoryDummy.findAll(employee)(finalE-1).employeeUsername should be (employee.employeeUsername)
    grdP.GenericRepositoryDummy.findAll(product)(finalP-1).porousware should be (product.porousware)
    grdPO.GenericRepositoryDummy.findAll(purchaseOrder)(finalPO-1).purchaseOrderStatus should be (purchaseOrder.purchaseOrderStatus)
  }
}