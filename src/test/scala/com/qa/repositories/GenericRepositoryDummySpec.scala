package com.qa.repositories
import org.scalatest._
import com.qa.repositories.GenericRepositoryDummy
import com.qa.entities.Product
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.PurchaseOrder
import com.qa.wotsunit.UnitSpec

class GenericRepositoryDummySpec extends UnitSpec{
      
  def testGet{
    "Calling get returns a function that" should "return an array of the given entity type" in{
      val arrayCO:Array[CustomerOrder] = GenericRepositoryDummy.get(new CustomerOrder)
      val arrayE:Array[Employee] = GenericRepositoryDummy.get(new Employee)
      val arrayP:Array[Product] = GenericRepositoryDummy.get(new Product)
      val arrayPO:Array[PurchaseOrder] = GenericRepositoryDummy.get(new PurchaseOrder)
      arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
      arrayE.getClass.getSimpleName+"" should be ("Employee[]")
      arrayP.getClass.getSimpleName+"" should be ("Product[]")
      arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
    }
    it should "return null if the entity type is not recognised" in{
      GenericRepositoryDummy.get("Test") should be (null)
      GenericRepositoryDummy.get(11) should be (null)
    }
  }
    
  def testPersist{
    "Calling persist returns a function that" should "append a single entity to the end of an array" in {
      val customerOrder = new CustomerOrder
      val employee = new Employee
      val product = new Product
      val purchaseOrder = new PurchaseOrder
      val initialCO = GenericRepositoryDummy.get(customerOrder).length
      val initialE = GenericRepositoryDummy.get(employee).length
      val initialP = GenericRepositoryDummy.get(product).length
      val initialPO = GenericRepositoryDummy.get(purchaseOrder).length
      GenericRepositoryDummy.persist(customerOrder)
      GenericRepositoryDummy.persist(employee)
      GenericRepositoryDummy.persist(product)
      GenericRepositoryDummy.persist(purchaseOrder)
      val finalCO = GenericRepositoryDummy.get(customerOrder).length
      val finalE = GenericRepositoryDummy.get(employee).length
      val finalP = GenericRepositoryDummy.get(product).length
      val finalPO = GenericRepositoryDummy.get(purchaseOrder).length
      finalCO should be (initialCO+1)
      finalE should be (initialE+1)
      finalP should be (initialP+1)
      finalPO should be (initialPO+1)
      GenericRepositoryDummy.get(customerOrder)(finalCO-1).customerOrderStatus should be (customerOrder.customerOrderStatus)
      GenericRepositoryDummy.get(employee)(finalE-1).employeeUsername should be (employee.employeeUsername)
      GenericRepositoryDummy.get(product)(finalP-1).porousware should be (product.porousware)
      GenericRepositoryDummy.get(purchaseOrder)(finalPO-1).purchaseOrderStatus should be (purchaseOrder.purchaseOrderStatus)
    }
  }
  
  def testUpdate{
    "Calling update returns a function that" should "replace the array entity at the given index with a new entity" in{
      val customerOrder = new CustomerOrder
      val employee = new Employee
      val product = new Product
      val purchaseOrder = new PurchaseOrder
      GenericRepositoryDummy.update(customerOrder,2)
      GenericRepositoryDummy.update(employee,3)
      GenericRepositoryDummy.update(product,5)
      GenericRepositoryDummy.update(purchaseOrder,7)
      val finalCO = GenericRepositoryDummy.get(customerOrder)(2)
      val finalE = GenericRepositoryDummy.get(employee)(3)
      val finalP = GenericRepositoryDummy.get(product)(5)
      val finalPO = GenericRepositoryDummy.get(purchaseOrder)(7)
      finalCO should be (customerOrder)
      finalE should be (employee)
      finalP should be (product)
      finalPO should be (purchaseOrder)
    }
  }
  testGet
  testPersist
  testUpdate
}