package com.qa.repositoryimplementations

import com.qa.repositories.GenericRepository
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.Employee
import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.MongoCursor
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Connection


/**
 * @author pnewman
 */
class GenericRepositoryActual[E:Manifest] {
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryMongo.class"))
  val mongoClient = MongoClient("localhost",27017)
  val mongoDB = mongoClient("FreshTech")
  val driverSQL = "com.mysql.jdbc.Driver"
  val urlSQL = "jdbc:mysql://localhost/FreshTech"
  val usernameSQL = "root"
  val passwordSQL = "academy"
  object GenericRepositoryActual extends GenericRepository[E]{
      def getDatabaseCustomerOrder:Array[CustomerOrder]={
        //TODO This is Java code
        def establishConnection:Connection={
          try{
            Class.forName(driverSQL)
            DriverManager.getConnection(urlSQL,usernameSQL,passwordSQL)
          }
          catch{
            //TODO Do this properly
            case e:Throwable => e.printStackTrace; null
          }
        }
        val conn = establishConnection
        val stmt = conn.createStatement
        null
      }
      def getDatabaseCustomerOrderLine(customerOrderId:Int):Array[CustomerOrder]={
        null
      }
      def getDatabaseEmployee:Array[Employee]={
        null
      }
      def getDatabaseProduct:Array[Product]={
        val productDoc = getCollection(mongoDB("Product")).toArray
        //logger.debug(productDoc.getClass+"")
        //logger.debug(productDoc(1).get("productID")+"")
        def createProducts(count:Int,productArray:Array[Product]):Array[Product]={
          if(count<productDoc.length){
            val productPage = productDoc(count)
            //logger.debug(productPage+"")
            val productId = (productPage.get("productID")+"").toInt
            //logger.debug(productId+"")
            val productName = productPage.get("productName")+""
            //logger.debug(productName)
            val image = productPage.get("image")+""
            val porousware = (productPage.get("porusware")+"").toBoolean
            //logger.debug(porousware+"")
            val orderId = 1
            //TODO orderId set to 1
            val aisle:Char = (productPage.get("aisle")+"")(0)
            //logger.debug(aisle+"")
            val shelf = (productPage.get("shelf")+"")toInt
            val newProduct = new Product(productId,productName,image,porousware,orderId,aisle,shelf)
            //logger.debug(newProduct+"")
            createProducts(count.+(1),productArray:+newProduct)
          }
          else{
            logger.debug("Products from database: "+productArray)
            productArray
          }
        }
        createProducts(0, Array[Product]())
      }
      def getDatabasePurchaseOrder:Array[PurchaseOrder]={
        null
      }
      def getCollection(collection:MongoCollection):MongoCursor={
        collection.find
      }
    def findAll:E=>Array[E]={entity =>
      entity match{
        case entity:CustomerOrder => getDatabaseCustomerOrder.asInstanceOf[Array[E]]
        case entity:Employee => getDatabaseEmployee.asInstanceOf[Array[E]]
        case entity:Product => getDatabaseProduct.asInstanceOf[Array[E]]
        case entity:PurchaseOrder => getDatabasePurchaseOrder.asInstanceOf[Array[E]]
        case _ =>{
         logger.error("Entity of type {} not handled by findAll method",entity.getClass.getSimpleName)
         logger.warn("Method will return null")
         null.asInstanceOf[Array[E]]
        }
      }
    }
    def persist:E=>Unit={entity =>
      logger.debug("Persisting entity of type {}",entity.getClass.getSimpleName)
      def postAppend(array:Array[E]):Array[E]={
        array:+entity
      }
      entity match{
        case entity:CustomerOrder => 
        case entity:Employee => 
        case entity:Product => 
        case entity:PurchaseOrder => 
        case _ =>{
         logger.error("Entity of type {} not handled by persist method",entity.getClass.getSimpleName)
         logger.warn("Method will return null")
         null.asInstanceOf[Array[E]]
        }
      }
    }
    def persistArray:Array[E]=>Unit={entityArray =>
      def postAppendArray(count:Int,array:Array[E]):Array[E]={
        if(count<entityArray.length){
        logger.debug("persistArray: start array length: {}",array.length+"")
        val nextArray = array:+entityArray(count)
        logger.debug("persistArray: end array length: {}",nextArray.length+"")
        postAppendArray(count+1,nextArray)
        }
        else{
          array
        }
      }
      val testEntity = entityArray.head
      testEntity match{
        case testEntity:CustomerOrder =>  
        case testEntity:Employee => 
        case testEntity:Product => 
        case testEntity:PurchaseOrder => 
        case _ =>{
          logger.error("Entity of type {} not handled by persistArray method",entityArray.getClass.getSimpleName)
          logger.warn("Method will return null")
        }
      }
    }
    def update:(E,Int)=>Unit = {(entity,index) =>
      entity match{
        case entity:CustomerOrder =>  
        case entity:Employee =>
        case entity:Product =>
        case entity:PurchaseOrder =>
        case _ =>{
          logger.error("Entity of type {} not handled by persistArray method",entity.getClass.getSimpleName)
          logger.warn("Method will return null")
        }
      }      
    }
  }  
}