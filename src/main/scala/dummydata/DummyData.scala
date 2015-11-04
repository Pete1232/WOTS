package dummydata

import com.qa.entities.Product
import scala.util.Random

/**
 * @author pnewman
 */
class DummyData(entries:Int) {
  val databaseProduct = new DummyBuilder[Product](entries)
  /**
   * This method builds an Array of Products to simulate a DB
   * @param counter
   */  
  def buildProduct(counter:Int){
    val randomInt = Random.nextInt()
    var randomFloat = Random.nextFloat()
    var randomBool = Random.nextBoolean()
    if(counter<entries){
      databaseProduct.addToArray(counter, new Product(counter+1,"Product: "+randomInt,randomInt,100*randomFloat,"Category: "+randomInt, "Description: "+randomInt, "Image: "+randomInt, randomBool))
      buildProduct(counter.+(1))
    }
  }
    

}