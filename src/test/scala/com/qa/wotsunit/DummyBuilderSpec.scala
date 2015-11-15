package com.qa.wotsunit

import com.qa.data.DummyBuilder
import scala.util.Random
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder

/**
 * @author pnewman
 */
class DummyBuilderSpec extends UnitSpec{

  def testAddToArray{
    "Calling addToArray" should "populate the given index of databaseArray (of any size) with an entity of the given type" in {
      val entries = Math.abs(Random.nextInt(200))
      val index = Math.abs(Random.nextInt(entries))
      val builderCO = new DummyBuilder[CustomerOrder](entries)
      val builderE = new DummyBuilder[Employee](entries)
      val builderP = new DummyBuilder[Product](entries)
      val builderPO = new DummyBuilder[PurchaseOrder](entries)
      val customerOrder=new CustomerOrder
      val employee=new Employee
      val product=new Product
      val purchaseOrder=new PurchaseOrder
    
      builderCO.addToArray(index,customerOrder)
      builderCO.databaseArray(index) should be (customerOrder)
      builderE.addToArray(index,employee)
      builderE.databaseArray(index) should be (employee)
      builderP.addToArray(index,product)
      builderP.databaseArray(index) should be (product)
      builderPO.addToArray(index,purchaseOrder)
      builderPO.databaseArray(index) should be (purchaseOrder)
    }
  }
  
  def testBuildEntityArray{
    "Calling buildEntityArray" should "populate ALL entries of databaseArray (of any size) with entities of the given type (when starting with counter 0)" in {
      val entries = Math.abs(Random.nextInt(200))
      val index = Math.abs(Random.nextInt(entries))
      val builderCO = new DummyBuilder[CustomerOrder](entries)
      val builderE = new DummyBuilder[Employee](entries)
      val builderP = new DummyBuilder[Product](entries)
      val builderPO = new DummyBuilder[PurchaseOrder](entries)
      val customerOrder=new CustomerOrder
      val employee=new Employee
      val product=new Product
      val purchaseOrder=new PurchaseOrder
    
      builderCO.buildEntityArray(0, customerOrder)  
      for(entry<-builderCO.databaseArray){
        entry should not be (null)
        entry.getClass should be (customerOrder.getClass)
      }
      builderE.buildEntityArray(0, employee)
      for(entry<-builderE.databaseArray){
        entry should not be (null)
        entry.getClass should be (employee.getClass)
      }
      builderP.buildEntityArray(0, product)
      for(entry<-builderP.databaseArray){
        entry should not be (null)
        entry.getClass should be (product.getClass)
      }
      builderPO.buildEntityArray(0, purchaseOrder)
      for(entry<-builderPO.databaseArray){
        entry should not be (null)
        entry.getClass should be (purchaseOrder.getClass)
      }
    }
  }
  testAddToArray
  testBuildEntityArray
}