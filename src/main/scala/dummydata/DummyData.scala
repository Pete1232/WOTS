package dummydata

import com.qa.entities.Product
import scala.util.Random

/**
 * @author pnewman
 */
object DummyData {
  val entries = 100
  val databaseProduct = new DummyBuilder[Product](entries)
  databaseProduct.buildEntityArray(0,entries,new Product())
}