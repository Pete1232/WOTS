

import dummydata.DummyData
import com.qa.repositoryimplementations.ProductRepositoryDummy
import com.qa.entities.Product
/* 
 * @author pnewman
 */
object RunTest {
  def main(args:Array[String]){
/*    val dummy = new DummyData(10)
    dummy.buildProduct(0)
    for(thing<-dummy.databaseProduct.databaseArray)
    println(thing.productId+" "+thing.productName+" £"+thing.price+" "+thing.porousware)*/
    val prd: ProductRepositoryDummy = new ProductRepositoryDummy()
    val array:Array[Product]=prd.findAll(new Product(1,"1",1,1,"1","1","1",true))
    for(thing<-array)
    println(thing.productId+" "+thing.productName+" £"+thing.price+" "+thing.porousware)
  }
}