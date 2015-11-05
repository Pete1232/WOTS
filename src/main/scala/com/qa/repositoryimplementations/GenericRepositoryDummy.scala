package com.qa.repositoryimplementations

import com.qa.repositories.GenericRepository
import com.qa.dummydata.DummyData
import com.qa.dummydata.DummyBuilder
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.PurchaseOrder

/**
 * @author pnewman
 */
class GenericRepositoryDummy[E] extends GenericRepository[E]{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryDummy.class"))
  /**
   * This method returns an Array from the dummy data based on the Entity type
   * @param entityType
   */
  def getEntityList[E](entity:E):Array[E]={
    logger.debug("Calling getEntityList with Entity type {}",entity.getClass.getSimpleName)
    val array:Array[E] = {
    (entity match{
      case entity:CustomerOrder => DummyData.databaseCustomerOrder.databaseArray
      case entity:Employee => DummyData.databaseEmployee.databaseArray
      case entity:Product => DummyData.databaseProduct.databaseArray
      case entity:PurchaseOrder => DummyData.databasePurchaseOrder.databaseArray
      case _ =>{
         logger.error("Entity of type {} not handled by getEntityList method",entity.getClass.getSimpleName)
         logger.warn("Program will terminate")
      }
    }).asInstanceOf[Array[E]]}
    logger.info("{} repository dummy methods available",entity.getClass().getSimpleName)
    array
  }
  /**
   * This method returns all entities in the given DummyData array
   * TODO This is redundant (see above) - remove?
   */
  def findAll[E](entity:E):Array[E]={
    logger.debug("Calling findAll with Entity type {}",entity.getClass.getSimpleName)
    getEntityList(entity:E)
  }
}