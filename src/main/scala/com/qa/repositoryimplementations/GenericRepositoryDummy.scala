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

  val dummyData:DummyData = new DummyData(20)
  
  def getEntityList[E](entityType:E):Array[E]={
    dummyData.buildProduct(0)
    (entityType match{
      case entityType:Product => dummyData.databaseProduct.databaseArray
    }).asInstanceOf[Array[E]]
  }
  
  def findAll(entity:E):Array[E]={
    getEntityList[E](entity:E)
  }
}