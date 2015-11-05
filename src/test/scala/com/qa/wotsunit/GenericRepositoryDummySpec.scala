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
    val grd = new GenericRepositoryDummy
  //TODO Add more methods as they are made
  "Calling getEntityList" should "return an array of the given entity type" in{
    val arrayCO:Array[CustomerOrder] = grd.getEntityList(customerOrder)
    val arrayE:Array[Employee] = grd.getEntityList(employee)
    val arrayP:Array[Product] = grd.getEntityList(product)
    val arrayPO:Array[PurchaseOrder] = grd.getEntityList(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
  "Calling findAll" should "return an array of the given entity type" in{
    val grd = new GenericRepositoryDummy
    val arrayCO:Array[CustomerOrder] = grd.findAll(customerOrder)
    val arrayE:Array[Employee] = grd.findAll(employee)
    val arrayP:Array[Product] = grd.findAll(product)
    val arrayPO:Array[PurchaseOrder] = grd.findAll(purchaseOrder)
    arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
    arrayE.getClass.getSimpleName+"" should be ("Employee[]")
    arrayP.getClass.getSimpleName+"" should be ("Product[]")
    arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
  }
}