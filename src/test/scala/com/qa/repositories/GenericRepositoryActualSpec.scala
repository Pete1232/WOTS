package com.qa.repositories

import com.qa.repositories.GenericRepositoryActual
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrderLine
import com.qa.data.DataConfig
import com.qa.wotsunit.UnitSpec

/**
 * @author pnewman
 */
class GenericRepositoryActualSpec extends UnitSpec {

  def connectionExists:Boolean = {
    (DataConfig.connectionMongo!==null) & (DataConfig.connectionSQL!==null)
  }
  
  def testGet{
    "Calling get returns a function that" should "return an array of the given entity type" in{
    if(!connectionExists){
      cancel("Test cancelled - At least one database connection is not available")
    }
    else{
      val arrayCO = GenericRepositoryActual.get(new CustomerOrder)
      val arrayE = GenericRepositoryActual.get(new Employee)
      val arrayPO = GenericRepositoryActual.get(new PurchaseOrder)
      arrayCO.getClass.getSimpleName+"" should be ("CustomerOrder[]")
      arrayE.getClass.getSimpleName+"" should be ("Employee[]")
      arrayPO.getClass.getSimpleName+"" should be ("PurchaseOrder[]")
      }
    }
    it should "return null if the entity type is not recognised" in{
      if(!connectionExists){
        cancel("Test cancelled - At least one database connection is not available")
      }
      else{
        GenericRepositoryActual.get("Test") should be (null)
        GenericRepositoryActual.get(11) should be (null)
      }
    }
  }

  def testGetCustomerOrderLineByOrderId{
    "Calling getCustomerOrderLineByOrderId" should "return an array of CustomerOrderLine" in{
      if(!connectionExists){
        cancel("Test cancelled - At least one database connection is not available")
      }
      else{
        GenericRepositoryActual.getCustomerOrderLineByOrderId(1).getClass.getSimpleName should be ("CustomerOrderLine[]")
      }
    }
    it should "only contain order lines with the given Id" in{
      if(!connectionExists){
        cancel("Test cancelled - At least one database connection is not available")
      }
      else{
        val orderLines = GenericRepositoryActual.getCustomerOrderLineByOrderId(1)
        for(orderLine<-orderLines){
          orderLine.orderId_ should be (1)
        }
      }
    }      
  }
  
  def testGetProductByOrderLine{
    "Calling getProductByOrderLine" should "return a Product" in{
      if(!connectionExists){
        cancel("Test cancelled - At least one database connection is not available")
      }
      else{
        GenericRepositoryActual.getProductByOrderLine(new CustomerOrderLine(1)).getClass.getSimpleName should be ("Product")
      }
    }
    it should "have the same productId as the given order line" in{
      if(!connectionExists){
        cancel("Test cancelled - At least one database connection is not available")
      }
      else{
        val product = GenericRepositoryActual.getProductByOrderLine(new CustomerOrderLine(1))
        product.productId_ should be (1)
      }
    }
  }
  
  testGet
  testGetCustomerOrderLineByOrderId
  testGetProductByOrderLine
  
}