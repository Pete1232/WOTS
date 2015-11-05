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
  "Calling findAll" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = grdCO.GenericRepositoryDummy.findAll(customerOrder)
    val arrayE:Array[Employee] = grdE.GenericRepositoryDummy.findAll(employee)
    val arrayP:Array[Product] = grdP.GenericRepositoryDummy.findAll(product)
    val arrayPO:Array[PurchaseOrder] = grdPO.GenericRepositoryDummy.findAll(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
}