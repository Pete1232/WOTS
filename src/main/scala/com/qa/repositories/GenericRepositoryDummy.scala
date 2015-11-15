package com.qa.repositories

import com.qa.data.DummyData
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrderLine

/**
 * This object implements GenericRepository with CRUD methods for DummyData
 * @author pnewman
 */

object GenericRepositoryDummy extends GenericRepository{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryDummy.class"))
  
  /**
   * This method returns a dummy data array of the given entity type
   * @param E
   * @return Array[E]
   */
  def get[E](entity:E):Array[E]={
    def getDatabaseCustomerOrder:Array[CustomerOrder]={
      DummyData.databaseCustomerOrder.databaseArray
    }
  
    def getDatabaseEmployee:Array[Employee]={
      DummyData.databaseEmployee.databaseArray
    }
  
    def getDatabaseProduct:Array[Product]={
      DummyData.databaseProduct.databaseArray
    }
  
    def getDatabasePurchaseOrder:Array[PurchaseOrder]={
      DummyData.databasePurchaseOrder.databaseArray
    }
    entity match{
      case entity:CustomerOrder => getDatabaseCustomerOrder.asInstanceOf[Array[E]]
      case entity:Employee => getDatabaseEmployee.asInstanceOf[Array[E]]
      case entity:Product => getDatabaseProduct.asInstanceOf[Array[E]]
      case entity:PurchaseOrder => getDatabasePurchaseOrder.asInstanceOf[Array[E]]
      case _ =>{
       logger.error("Entity of type {} not handled by findAll method",entity.getClass.getSimpleName)
       logger.warn("Method will return null")
       null.asInstanceOf[Array[E]]
      }
    }
  }
  
  /**
   * This method persists a given entity in the corresponding dummy data array
   * @param E
   */
  def persist[E](entity:E){
    entity match{
      case entity:CustomerOrder => DummyData.databaseCustomerOrder.databaseArray=(get(new CustomerOrder):+entity).asInstanceOf[Array[CustomerOrder]]
      case entity:Employee => DummyData.databaseEmployee.databaseArray=(get(new Employee):+entity).asInstanceOf[Array[Employee]]
      case entity:Product => DummyData.databaseProduct.databaseArray=(get(new Product):+entity).asInstanceOf[Array[Product]]
      case entity:PurchaseOrder => DummyData.databasePurchaseOrder.databaseArray=(get(new PurchaseOrder):+entity).asInstanceOf[Array[PurchaseOrder]]
      case _ =>{
       logger.error("Entity of type {} not handled by persist method",entity.getClass.getSimpleName)
       logger.warn("Method will return null")
       null.asInstanceOf[Array[E]]
      }
    }
  }
  
  /**
   * This method updates an entity at the given index of the corresponding dummy data array
   * @param E
   * @param Int
   */
  def update[E](entity:E,index:Int) {
    entity match{
      case entity:CustomerOrder =>  DummyData.databaseCustomerOrder.databaseArray(index)=entity
      case entity:Employee => DummyData.databaseEmployee.databaseArray(index)=entity
      case entity:Product => DummyData.databaseProduct.databaseArray(index)=entity
      case entity:PurchaseOrder => DummyData.databasePurchaseOrder.databaseArray(index)=entity
      case _ =>{
        logger.error("Entity of type {} not handled by persistArray method",entity.getClass.getSimpleName)
        logger.warn("Method will return null")
      }
    }      
  }

  /**
   * This method returns an array of CustomerOrderLine with the given orderId
   * @param Int
   * @return Array[CustomerOrderLine]
   */
  def getCustomerOrderLineByOrderId(customerOrderId: Int): Array[CustomerOrderLine] = {
    Array[CustomerOrderLine](new CustomerOrderLine(customerOrderId,1,10),new CustomerOrderLine(customerOrderId,2,20))
  }
  /**
   * This method returns a product corresponding to the given CustomerOrderLine
   * @param CustomerOrderLine
   * @return Product
   */
  def getProductByOrderLine(orderLine:CustomerOrderLine):Product={
    val productData = get(new Product)
    def populateProducts(count:Int,orderLine:CustomerOrderLine):Product={
        if(productData(count).productId_ == orderLine.productId_){
          productData(count)
        }
        else{
          if(count<productData.length){
            populateProducts(count.+(1),orderLine)
          }
          else{
            null
        }
      }
    }
    populateProducts(0, orderLine)
  }
}