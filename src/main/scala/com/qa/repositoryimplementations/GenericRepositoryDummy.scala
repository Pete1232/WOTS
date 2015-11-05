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
class GenericRepositoryDummy[E:Manifest] extends GenericRepository[E]{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryDummy.class"))
  /**
   * This method returns an Array from the dummy data based on the Entity type
   * @param entity
   */
  def getEntityList(entity:E):Array[E]={
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
    logger.info("Dummy database{} retrieved",entity.getClass.getSimpleName)
    array
  }
  /**
   * This method returns all entities in the given DummyData array
   * TODO This is redundant (see above) - remove?
   */
  def findAll(entity:E):Array[E]={
    logger.debug("Calling findAll with Entity type {}",entity.getClass.getSimpleName)
    getEntityList(entity:E)
  }
  /**
   * This method sets the DummyData databaseArray of the given type as the given entity
   * @param entity
   */
  def persist(entity:E){
    logger.debug("Calling persist with Entity type {}",entity.getClass.getSimpleName)
    if(GenericRepositoryDummy.determineArray(entity)){
      logger.info("Replacing database{} with new Array[{}](length)",entity.getClass.getSimpleName,entity.getClass.getSimpleName)
    }
    else{
      logger.info("Adding new {} to database",entity.getClass.getSimpleName)
      entity+:DummyData.databaseProduct.databaseArray
    }
  }
  object GenericRepositoryDummy{
    def determineArray[E](entity:E):Boolean={
      if(entity.getClass.isArray){
        logger.debug("Determined type as Array")
        true
      }
      else{
        logger.debug("Determined type as !Array")
        false
      }
    }
    def determineClass[E](entity:E):E={
      val entityType:E =(
      entity match{
        case entity:CustomerOrder => new CustomerOrder
        case entity:Employee => new Employee
        case entity:Product => new Product
        case entity:PurchaseOrder => new PurchaseOrder
        case _ =>{
         logger.error("Entity of type {} not handled by getEntityList method",entity.getClass.getSimpleName)
         logger.warn("Program will terminate")
        }
      }).asInstanceOf[E]
      entityType
    }
  }
  
  
}