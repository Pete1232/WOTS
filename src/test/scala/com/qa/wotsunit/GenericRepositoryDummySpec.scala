package com.qa.wotsunit

import org.scalatest._
import com.qa.repositoryimplementations.GenericRepositoryDummy
import com.qa.dummydata.DummyBuilder
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
    val customerOrder = new CustomerOrder
    val employee = new Employee
    val product = new Product
    val purchaseOrder = new PurchaseOrder
    var customerOrderArray:Array[CustomerOrder] = new Array[CustomerOrder](3)
    for(i<-0 until customerOrderArray.length)
      customerOrderArray(i)=new CustomerOrder
    var employeeArray:Array[Employee] = new Array[Employee](3)
    for(i<-0 until employeeArray.length)
      employeeArray(i)=new Employee
    var productArray:Array[Product] = new Array[Product](3)
    for(i<-0 until productArray.length)
      productArray(i)=new Product
    var purchaseOrderArray:Array[PurchaseOrder] = new Array[PurchaseOrder](3)
    for(i<-0 until purchaseOrderArray.length)
      purchaseOrderArray(i)=new PurchaseOrder
    val grdCO = new GenericRepositoryDummy[CustomerOrder]
    val grdE = new GenericRepositoryDummy[Employee]
    val grdP = new GenericRepositoryDummy[Product]
    val grdPO = new GenericRepositoryDummy[PurchaseOrder]
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
  "Calling persist returns a function that" should "append each element of a given array to another array" in{
    val initialCO = grdCO.GenericRepositoryDummy.findAll(customerOrder).length
    val initialE = grdE.GenericRepositoryDummy.findAll(employee).length
    val initialP = grdP.GenericRepositoryDummy.findAll(product).length
    val initialPO = grdPO.GenericRepositoryDummy.findAll(purchaseOrder).length
    grdCO.GenericRepositoryDummy.persistArray(customerOrderArray)
    grdE.GenericRepositoryDummy.persistArray(employeeArray)
    grdP.GenericRepositoryDummy.persistArray(productArray)
    grdPO.GenericRepositoryDummy.persistArray(purchaseOrderArray)
    val finalCO = grdCO.GenericRepositoryDummy.findAll(customerOrder).length
    val finalE = grdE.GenericRepositoryDummy.findAll(employee).length
    val finalP = grdP.GenericRepositoryDummy.findAll(product).length
    val finalPO = grdPO.GenericRepositoryDummy.findAll(purchaseOrder).length
    finalCO should be (initialCO+customerOrderArray.length)
    finalE should be (initialE+employeeArray.length)
    finalP should be (initialP+productArray.length)
    finalPO should be (initialPO+purchaseOrderArray.length)
    grdCO.GenericRepositoryDummy.findAll(customerOrder)(finalCO-1).customerOrderStatus_ should be (customerOrder.customerOrderStatus_)
    grdE.GenericRepositoryDummy.findAll(employee)(finalE-1).employeeUsername_ should be (employee.employeeUsername_)
    grdP.GenericRepositoryDummy.findAll(product)(finalP-1).porousware_ should be (product.porousware_)
    grdPO.GenericRepositoryDummy.findAll(purchaseOrder)(finalPO-1).purchaseOrderStatus_ should be (purchaseOrder.purchaseOrderStatus_)
  }
  "Calling update returns a function that" should "replace the array entity at the given index with a new entity" in{
    grdCO.GenericRepositoryDummy.update(customerOrder,2)
    grdE.GenericRepositoryDummy.update(employee,3)
    grdP.GenericRepositoryDummy.update(product,5)
    grdPO.GenericRepositoryDummy.update(purchaseOrder,7)
    val finalCO = grdCO.GenericRepositoryDummy.findAll(customerOrder)(2)
    val finalE = grdE.GenericRepositoryDummy.findAll(employee)(3)
    val finalP = grdP.GenericRepositoryDummy.findAll(product)(5)
    val finalPO = grdPO.GenericRepositoryDummy.findAll(purchaseOrder)(7)
    finalCO should be (customerOrder)
    finalE should be (employee)
    finalP should be (product)
    finalPO should be (purchaseOrder)
  }
}