/*package com.qa.wotsunit

import org.scalatest._
import com.qa.repositoryimplementations.GenericRepositoryDummy
import com.qa.entities.Product
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.PurchaseOrder

class GenericRepositoryDummySpec extends UnitSpec{
    val customerOrder = new CustomerOrder
    val employee = new Employee
    val product = new Product
    val purchaseOrder = new PurchaseOrder
    val grdCO = new GenericRepositoryDummy[CustomerOrder]
    val grdE = new GenericRepositoryDummy[Employee]
    val grdP = new GenericRepositoryDummy[Product]
    val grdPO = new GenericRepositoryDummy[PurchaseOrder]
  //TODO Add more methods as they are made
  "Calling getEntityList" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = grdCO.getEntityList(customerOrder)
    val arrayE:Array[Employee] = grdE.getEntityList(employee)
    val arrayP:Array[Product] = grdP.getEntityList(product)
    val arrayPO:Array[PurchaseOrder] = grdPO.getEntityList(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
  "Calling findAll" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = grdCO.findAll(customerOrder)
    val arrayE:Array[Employee] = grdE.findAll(employee)
    val arrayP:Array[Product] = grdP.findAll(product)
    val arrayPO:Array[PurchaseOrder] = grdPO.findAll(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
  "Calling determineArray with parameter entity" should "return true if and only if entity is an Array" in{
    grdE.GenericRepositoryDummy.determineArray(new Product) should be (false)
    grdE.GenericRepositoryDummy.determineArray(new Array[Product](5)) should be (true)
    grdE.GenericRepositoryDummy.determineArray(new Employee) should be (false)
    grdE.GenericRepositoryDummy.determineArray(new Array[Employee](3)) should be (true)
    grdP.GenericRepositoryDummy.determineArray(new Product) should be (false)
    grdP.GenericRepositoryDummy.determineArray(new Array[Product](5)) should be (true)
    grdPO.GenericRepositoryDummy.determineArray(new Employee) should be (false)
    grdCO.GenericRepositoryDummy.determineArray(new Array[Employee](3)) should be (true)  
  }
}*/