

import dummydata.DummyData
import com.qa.repositoryimplementations.ProductRepositoryDummy
import com.qa.entities.Product
/* 
 * @author pnewman
 */
object RunTest {
  def main(args:Array[String]){
    val productRepositoryDummy: ProductRepositoryDummy = new ProductRepositoryDummy()
    val array:Array[Product]=productRepositoryDummy.findAll(new Product())
    for(product<-array)
    println(product.productId+" "+product.productName+" £"+product.price+" "+product.porousware)
  }
}