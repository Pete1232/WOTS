package dummydata

import scala.util.Random
import com.qa.entities.Product

/**
 * @author pnewman
 */
class DummyBuilder[E:Manifest](size:Int){
  val databaseArray:Array[E]= new Array[E](size)
  def addToArray(index:Int, entity:E){
      databaseArray(index) = entity
  }
}