package com.qa.data

import scala.util.Random
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.PurchaseOrder
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
          
/**
 * This class contains methods to automatically populate DummyData arrays
 * @author pnewman
 */
class DummyBuilder[E:Manifest](size:Int){
  val logger = Logger(LoggerFactory.getLogger("DummyBuilder.class"))
  var databaseArray:Array[E]= new Array[E](size)
  logger.debug("Initialised databaseArray of size {} and type {}",size+"",databaseArray.getClass.getSimpleName)
  /**
   * This generic method fills databaseArray with the relevant entities
   * @param counter
   * @param entries
   * @param entity
   */
  def buildEntityArray(counter:Int,entity:E){
    val randomInt = Math.abs(Random.nextInt(1000))
    val randomId = Math.abs(Random.nextInt(100))
    val randomShelf = Math.abs(Random.nextInt(50))
    val randomBool = Random.nextBoolean
    val randomChar = (Random.nextInt(25)+65).toChar
    if(counter<=3 || counter==size)
      logger.debug("Populating DummyData Array of {}. Count: {} out of {}. (Checks first 3 and final)",entity.getClass.getSimpleName,counter+"",size+"")
    if(counter<size){
      
      entity match{
        case entity:CustomerOrder =>{
          addToArray(counter, new CustomerOrder(counter+1,"Status: "+randomInt,"Address: "+randomInt,0).asInstanceOf[E])
          buildEntityArray(counter.+(1),entity.asInstanceOf[E])
        }
        case entity:Employee =>{
          addToArray(counter, new Employee(counter+1,"Name: "+randomInt,"Username: "+randomInt,"Password: "+randomInt,randomInt).asInstanceOf[E])
          buildEntityArray(counter.+(1),entity.asInstanceOf[E])
        }
        case entity:Product => {
          addToArray(counter, new Product(counter+1,"Product: "+randomInt,"Image: "+randomInt,randomBool,randomShelf,randomChar,randomId, randomId).asInstanceOf[E])
          buildEntityArray(counter.+(1),entity.asInstanceOf[E])
        }
        case entity:PurchaseOrder =>{
          addToArray(counter, new PurchaseOrder(counter+1,randomInt,"Status: "+randomInt).asInstanceOf[E])
          buildEntityArray(counter.+(1),entity.asInstanceOf[E])          
        }
        case _ => {
          logger.error("Entity of type {} not handled by buildEntityArray method",entity.getClass.getSimpleName)
          logger.warn("Program will run but DummyData.database{} will be empty",entity.getClass.getSimpleName)
        }
      }
    }
    if(counter==size)
      logger.info("DummyData {} table has been successfully initialised.",entity.getClass.getSimpleName)
  }
  /**
   * This generic method adds an entity to databaseArray of given type at given index
   */
  def addToArray(index:Int,entity:E){
    databaseArray(index)=(entity match{
      case entity:CustomerOrder=>entity
      case entity:Employee=>entity
      case entity:Product=>entity
      case entity:PurchaseOrder=>entity
      case _ => {
        logger.error("Entity of type {} not handled by addToArray method",entity.getClass.getSimpleName)
        logger.warn("Program will terminate - Add a case for entity type {}",entity.getClass.getSimpleName)
      }
    }).asInstanceOf[E]
    if(index<=3)
      logger.debug("Adding new {}.(Checks first 3 only)",entity.getClass.getSimpleName)
  }
}