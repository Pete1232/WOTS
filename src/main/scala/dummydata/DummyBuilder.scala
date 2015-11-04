package dummydata

import scala.util.Random
import com.qa.entities.Product

/**
 * This class contains methods to automatically populate DummyData arrays
 * @author pnewman
 */
class DummyBuilder[E:Manifest](size:Int){
  val databaseArray:Array[E]= new Array[E](size)
  /**
   * This generic method fills databaseArray with the relevant entities
   * @param counter
   * @param entries
   * @param entity
   */
  def buildEntityArray(counter:Int,entries:Int,entity:E){
    val randomInt = Random.nextInt()
    var randomFloat = Random.nextFloat()
    var randomBool = Random.nextBoolean()
    if(counter<entries){
      addToArray(counter, new Product(counter+1,"Product: "+randomInt,randomInt,100*randomFloat,"Category: "+randomInt, "Description: "+randomInt, "Image: "+randomInt, randomBool).asInstanceOf[E])
      buildEntityArray(counter.+(1),entries,entity)
    }
  }
  /**
   * This generic method adds an entity to databaseArray of given type at given index
   */
  def addToArray(index:Int,entity:E){
    databaseArray(index)=(entity match{
      case entity:Product => entity
    }).asInstanceOf[E]
  }
}