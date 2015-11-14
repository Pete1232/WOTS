package com.qa.repositoryimplementations

import com.qa.repositories.GenericRepository
import com.qa.data.DummyData
import com.qa.data.DummyBuilder
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder

/**
 * This class implements GenericRepository with CRUD methods for DummyData
 * @author pnewman
 */
class GenericRepositoryDummy[E:Manifest]{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryDummy.class"))
  object GenericRepositoryDummy extends GenericRepository[E]{
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
    def findAll:E=>Array[E]={entity =>
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
    def persist:E=>Unit={entity =>
      logger.debug("Persisting entity of type {}",entity.getClass.getSimpleName)
      def postAppend(array:Array[E]):Array[E]={
        array:+entity
      }
      entity match{
        case entity:CustomerOrder => DummyData.databaseCustomerOrder.databaseArray=postAppend(getDatabaseCustomerOrder.asInstanceOf[Array[E]]).asInstanceOf[Array[CustomerOrder]]
        case entity:Employee => DummyData.databaseEmployee.databaseArray=postAppend(getDatabaseEmployee.asInstanceOf[Array[E]]).asInstanceOf[Array[Employee]]
        case entity:Product => DummyData.databaseProduct.databaseArray=postAppend(getDatabaseProduct.asInstanceOf[Array[E]]).asInstanceOf[Array[Product]]
        case entity:PurchaseOrder => DummyData.databasePurchaseOrder.databaseArray=postAppend(getDatabasePurchaseOrder.asInstanceOf[Array[E]]).asInstanceOf[Array[PurchaseOrder]]
        case _ =>{
         logger.error("Entity of type {} not handled by persist method",entity.getClass.getSimpleName)
         logger.warn("Method will return null")
         null.asInstanceOf[Array[E]]
        }
      }
    }
    def persistArray:Array[E]=>Unit={entityArray =>
      def postAppendArray(count:Int,array:Array[E]):Array[E]={
        if(count<entityArray.length){
        logger.debug("persistArray: start array length: {}",array.length+"")
        val nextArray = array:+entityArray(count)
        logger.debug("persistArray: end array length: {}",nextArray.length+"")
        postAppendArray(count+1,nextArray)
        }
        else{
          array
        }
      }
      val testEntity = entityArray.head
      testEntity match{
        case testEntity:CustomerOrder =>  DummyData.databaseCustomerOrder.databaseArray=postAppendArray(0,getDatabaseCustomerOrder.asInstanceOf[Array[E]]).asInstanceOf[Array[CustomerOrder]]
        case testEntity:Employee => DummyData.databaseEmployee.databaseArray=postAppendArray(0,getDatabaseEmployee.asInstanceOf[Array[E]]).asInstanceOf[Array[Employee]]
        case testEntity:Product => DummyData.databaseProduct.databaseArray=postAppendArray(0,getDatabaseProduct.asInstanceOf[Array[E]]).asInstanceOf[Array[Product]]
        case testEntity:PurchaseOrder => DummyData.databasePurchaseOrder.databaseArray=postAppendArray(0,getDatabasePurchaseOrder.asInstanceOf[Array[E]]).asInstanceOf[Array[PurchaseOrder]]
        case _ =>{
          logger.error("Entity of type {} not handled by persistArray method",entityArray.getClass.getSimpleName)
          logger.warn("Method will return null")
        }
      } 
    }
    def update:(E,Int)=>Unit = {(entity,index) =>
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
  }  
}