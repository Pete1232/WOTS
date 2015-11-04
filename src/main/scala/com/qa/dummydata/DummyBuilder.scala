package com.qa.dummydata

import scala.util.Random
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * This class contains methods to automatically populate DummyData arrays
 * @author pnewman
 */
class DummyBuilder[E:Manifest](size:Int){
  val logger = Logger(LoggerFactory.getLogger("DummyBuilder.class"))
  logger.debug("Initialising databaseArray of size {}",size+"")
  val databaseArray:Array[E]= new Array[E](size)
  /**
   * This generic method fills databaseArray with the relevant entities
   * @param counter
   * @param entries
   * @param entity
   */
  def buildEntityArray(counter:Int,entries:Int,entity:E){
    val randomInt = Math.abs(Random.nextInt())
    val randomFloat = Math.abs(Random.nextFloat())
    val randomBool = Random.nextBoolean()
    if(counter<=5 || counter==entries)
      logger.debug("Populating DummyData Array of {}. Count: {} out of {}. (Checks first 5 only)",entity.getClass.getSimpleName,counter+"",entries+"")
    if(counter<entries){
      addToArray(counter, new Product(counter+1,"Product: "+randomInt,randomInt,100*randomFloat,"Category: "+randomInt, "Description: "+randomInt, "Image: "+randomInt, randomBool).asInstanceOf[E])
      buildEntityArray(counter.+(1),entries,entity)
    }
    if(counter==entries)
      logger.info("DummyData {} table has been initialised.",entity.getClass.getSimpleName)
  }
  /**
   * This generic method adds an entity to databaseArray of given type at given index
   */
  def addToArray(index:Int,entity:E){
    databaseArray(index)=(entity match{
      case entity:Product => entity
    }).asInstanceOf[E]
    if(index<=5)
      logger.debug("Adding new {}.(Checks first 5 only)",entity.getClass.getSimpleName)

  }
}