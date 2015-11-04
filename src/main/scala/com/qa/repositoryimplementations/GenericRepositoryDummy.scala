package com.qa.repositoryimplementations

import repositories.GenericRepository
import dummydata.DummyData
import dummydata.DummyBuilder
import scala.util.Random
import com.qa.entities.Product

/**
 * @author pnewman
 */
class GenericRepositoryDummy[E] extends GenericRepository[E]{
  /**
   * This method returns an Array from the dummy data based on the Entity type
   * @param entityType
   */
  def getEntityList[E](entity:E):Array[E]={
    (entity match{
      case entityType:Product => DummyData.databaseProduct.databaseArray
    }).asInstanceOf[Array[E]]
  }
  /**
   * This method returns all entities in the given DummyData array
   * TODO This is redundant (see above) - remove?
   */
  def findAll(entity:E):Array[E]={
    getEntityList[E](entity:E)
  }
}