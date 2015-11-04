package com.qa.repositoryimplementations

import com.qa.repositories.GenericRepository
import com.qa.dummydata.DummyData
import com.qa.dummydata.DummyBuilder
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

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
    (entity match{
      case entityType:Product => DummyData.databaseProduct.databaseArray
    }).asInstanceOf[Array[E]]
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