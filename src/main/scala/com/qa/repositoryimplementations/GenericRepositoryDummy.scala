package com.qa.repositoryimplementations

import com.qa.repositories.GenericRepository
import com.qa.dummydata.DummyData
import com.qa.dummydata.DummyBuilder
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder

/**
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
    /**
     * This method returns a get method to retrieve a Dummy database of the given type
     * @param entity
     */
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
  }  
}