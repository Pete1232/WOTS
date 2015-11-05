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
class GenericRepositoryDummy[E:Manifest] extends GenericRepository[E]{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryDummy.class"))
  object GenericRepositoryDummy{
    /**
     * This method executes a method based on the entity type of the given parameter
     * @param entity
     * @param callback
     */
    def findAll(entity:E)=(entityType:E)=>{
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
        case entity:CustomerOrder => getDatabaseCustomerOrder
        case entity:Employee => getDatabaseEmployee
        case entity:Product => getDatabaseProduct
        case entity:PurchaseOrder => getDatabasePurchaseOrder
        case _ =>{
         logger.error("Entity of type {} not handled by getEntityList method",entity.getClass.getSimpleName)
         logger.warn("Program will terminate")
        }
      }

    }
  }
  
  
}