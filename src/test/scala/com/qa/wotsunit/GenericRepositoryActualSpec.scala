/*package com.qa.wotsunit

import com.qa.repositories.GenericRepositoryActual
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder

*//**
 * @author pnewman
 *//*
class GenericRepositoryActualSpec extends UnitSpec {

  def testGet{
    "Calling get_ returns a function that" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = GenericRepositoryActual.get(new CustomerOrder)
    val arrayE:Array[Employee] = GenericRepositoryActual.get(new Employee)
    val arrayP:Array[Product] = GenericRepositoryActual.get(new Product)
    val arrayPO:Array[PurchaseOrder] = GenericRepositoryActual.get(new PurchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
    }
  }
    
  def testPersist{
    "Calling persist returns a function that" should "append a single entity to the end of an array" in {
      val customerOrder = new CustomerOrder
      val employee = new Employee
      val product = new Product
      val purchaseOrder = new PurchaseOrder
      val initialCO = GenericRepositoryActual.get(customerOrder).length
      val initialE = GenericRepositoryActual.get(employee).length
      val initialP = GenericRepositoryActual.get(product).length
      val initialPO = GenericRepositoryActual.get(purchaseOrder).length
      GenericRepositoryActual.persist(customerOrder)
      GenericRepositoryActual.persist(employee)
      GenericRepositoryActual.persist(product)
      GenericRepositoryActual.persist(purchaseOrder)
      val finalCO = GenericRepositoryActual.get(customerOrder).length
      val finalE = GenericRepositoryActual.get(employee).length
      val finalP = GenericRepositoryActual.get(product).length
      val finalPO = GenericRepositoryActual.get(purchaseOrder).length
      finalCO should be (initialCO+1)
      finalE should be (initialE+1)
      finalP should be (initialP+1)
      finalPO should be (initialPO+1)
      GenericRepositoryActual.get(customerOrder)(finalCO-1).customerOrderStatus should be (customerOrder.customerOrderStatus)
      GenericRepositoryActual.get(employee)(finalE-1).employeeUsername should be (employee.employeeUsername)
      GenericRepositoryActual.get(product)(finalP-1).porousware should be (product.porousware)
      GenericRepositoryActual.get(purchaseOrder)(finalPO-1).purchaseOrderStatus should be (purchaseOrder.purchaseOrderStatus)
    }
  }
  
  def testUpdate{
    "Calling update returns a function that" should "replace the array entity at the given index with a new entity" in{
      val customerOrder = new CustomerOrder
      val employee = new Employee
      val product = new Product
      val purchaseOrder = new PurchaseOrder
      GenericRepositoryActual.update(customerOrder,2)
      GenericRepositoryActual.update(employee,3)
      GenericRepositoryActual.update(product,5)
      GenericRepositoryActual.update(purchaseOrder,7)
      val finalCO = GenericRepositoryActual.get(customerOrder)(2)
      val finalE = GenericRepositoryActual.get(employee)(3)
      val finalP = GenericRepositoryActual.get(product)(5)
      val finalPO = GenericRepositoryActual.get(purchaseOrder)(7)
      finalCO should be (customerOrder)
      finalE should be (employee)
      finalP should be (product)
      finalPO should be (purchaseOrder)
    }
  }
  testGet
  testPersist
  testUpdate
  
}*/