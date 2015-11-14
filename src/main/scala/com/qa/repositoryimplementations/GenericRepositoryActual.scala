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
import java.sql.PreparedStatement
import com.qa.entities.CustomerOrder
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine

/**
 * @author pnewman
 */
class GenericRepositoryActual[E: Manifest] {
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryMongo.class"))
  val mongoClient = MongoClient("localhost", 27017)
  val mongoDB = mongoClient("FreshTech")
  val driverSQL = "com.mysql.jdbc.Driver"
  val urlSQL = "jdbc:mysql://localhost/NBGardens"
  val usernameSQL = "root"
  val passwordSQL = "academy"

  object GenericRepositoryActual extends GenericRepository[E] {
    def getDatabaseCustomerOrder: Array[CustomerOrder] = {
      //TODO This is Java code
      def establishConnection: Connection = {
        try {
          DriverManager.getConnection(urlSQL, usernameSQL, passwordSQL)
        } catch {
          //TODO Do this properly
          case e: Throwable => e.printStackTrace; null
        }
      }
      val conn = establishConnection
      val stmt = conn.createStatement
      val ps: PreparedStatement = conn.prepareStatement("SELECT * FROM customerorder")
      logger.debug("Statement: " + ps)
      val rs = ps.executeQuery
      def createOrders(orders: Array[CustomerOrder]): Array[CustomerOrder] = {
        if (rs.next) {
          val orderId = rs.getInt("CustomerOrderId")
          val orderStatus = rs.getString("CustomerOrderStatus")
          val deliveryAddress = rs.getString("AddressId")
          val employeeId = rs.getInt("EmployeeId")
          val newOrder = new CustomerOrder(orderId, orderStatus, deliveryAddress, employeeId)
          createOrders(orders :+ newOrder)
        } else {
          orders
        }
      }
      createOrders(Array[CustomerOrder]())
    }
    def getDatabaseEmployee: Array[Employee] = {
      //TODO This is Java code
      def establishConnection: Connection = {
        try {
          DriverManager.getConnection(urlSQL, usernameSQL, passwordSQL)
        } catch {
          //TODO Do this properly
          case e: Throwable => e.printStackTrace; null
        }
      }
      val conn = establishConnection
      val stmt = conn.createStatement
      val ps: PreparedStatement = conn.prepareStatement("SELECT * FROM employee")
      logger.debug("Statement: " + ps)
      val rs = ps.executeQuery
      //logger.debug("Result set: "+rs.next)
      def createEmployees(employees: Array[Employee]): Array[Employee] = {
        if (rs.next) {
          //logger.debug("Result set next exists")
          val employeeId = rs.getInt("EmployeeId")
          //logger.debug("Order Id: "+orderId)
          val employeeName = rs.getString("EmployeeName")
          val employeeUsername = rs.getString("EmployeeUsername")
          val employeePassword = rs.getString("EmployeePassword")
          val employeeAccessLevel = rs.getInt("AccessLevel")
          //logger.debug("Employee Id: "+employeeId)
          val newEmployee = new Employee(employeeId, employeeName, employeeUsername, employeePassword, employeeAccessLevel)
          //logger.debug("New customer order: "+newOrder)
          createEmployees(employees :+ newEmployee)
        } else {
          employees
        }
      }
      createEmployees(Array[Employee]())
    }
    def getDatabaseCustomerOrderLine(customerOrderId: Int): Array[CustomerOrderLine] = {
       def establishConnection: Connection = {
        try {
          DriverManager.getConnection(urlSQL, usernameSQL, passwordSQL)
        } catch {
          //TODO Do this properly
          case e: Throwable => e.printStackTrace; null
        }
      }
       
      val conn = establishConnection
      val stmt = conn.createStatement
      val ps: PreparedStatement = conn.prepareStatement("SELECT * FROM customerorderline WHERE CustomerOrder_CustomerOrderId="+customerOrderId)
      logger.debug("Statement: " + ps)
      val rs = ps.executeQuery
      //logger.debug("Result set: "+rs.next)
      def createCustomerOrderLine(orderLines: Array[CustomerOrderLine]): Array[CustomerOrderLine] = {
        if (rs.next) {
          //logger.debug("Result set next exists")
          val orderId = rs.getInt("CustomerOrder_CustomerOrderId")
          //logger.debug("Order Id: "+orderId)
          val productId = rs.getInt("ProductID")
          val quantity = rs.getInt("Quantity")
          //logger.debug("Employee Id: "+employeeId)
          val newOrderLine = new CustomerOrderLine(orderId,productId,quantity)
          //logger.debug("New customer order: "+newOrder)
          createCustomerOrderLine(orderLines :+ newOrderLine)
        } else {
          orderLines
        }
      }
      createCustomerOrderLine(Array[CustomerOrderLine]())
    }
/*    def getDatabaseProduct: Array[Product] = {
      val productDoc = getCollection(mongoDB("Product")).toArray
      logger.debug("Entering getDatabaseProduct method with {} Products", productDoc.length + "")
      //logger.debug(productDoc.getClass+"")
      //logger.debug(productDoc(1).get("productID")+"")
      def createProducts(count: Int, productArray: Array[Product]): Array[Product] = {
        if (count < productDoc.length) {
          logger.debug("Adding new product")
          val productPage = productDoc(count)
          //logger.debug(productPage+"")
          val productId = (productPage.get("productID") + "").toInt
          //logger.debug(productId+"")
          val productName = productPage.get("productName") + ""
          //logger.debug(productName)
          val image = productPage.get("image") + ""
          val porousware = (productPage.get("porusware") + "").toBoolean
          //logger.debug(porousware+"")
          val orderId = 2
          //TODO orderId set to 1
          val aisle: Char = (productPage.get("aisle") + "")(0)
          //logger.debug(aisle+"")
          val shelf = (productPage.get("shelf") + "")toInt
          val newProduct = new Product(productId, productName, image, porousware, orderId, aisle, shelf)
          //logger.debug(newProduct+"")
          createProducts(count.+(1), productArray :+ newProduct)
        } else {
          logger.debug("Products from database: " + productArray)
          productArray
        }
      }
      createProducts(0, Array[Product]())
    }*/
    
    def getProductByOrderLine(orderLine:CustomerOrderLine):Product={
      val productDoc = getCollection(mongoDB("Product")).toArray
      def findProduct(count:Int):Product = {
        val productId = (productDoc(count).get("productID") + "").toInt
        if(productId==orderLine.productId_){
          val productName = productDoc(count).get("productName") + ""
          val image = productDoc(count).get("image") + ""
          val porousware = (productDoc(count).get("porusware") + "").toBoolean
          val orderId = orderLine.orderId_
          val aisle: Char = (productDoc(count).get("aisle") + "")(0)
          val shelf = (productDoc(count).get("shelf") + "")toInt
          val quantity = orderLine.quantity_
          new Product(productId, productName, image, porousware, orderId, aisle, shelf, quantity)
        }
        else{
          findProduct(count.+(1))
        }
      }
      findProduct(0)
    }
    
    def getDatabasePurchaseOrder: Array[PurchaseOrder] = {
      //TODO empty method stub
      null
    }
    def getCollection(collection: MongoCollection): MongoCursor = {
      logger.debug("Entering getCollection method")
      collection.find
    }
   /* def findAll: E => Array[E] = { entity =>
      entity match {
        case entity: CustomerOrder => getDatabaseCustomerOrder.asInstanceOf[Array[E]]
        case entity: Employee      => getDatabaseEmployee.asInstanceOf[Array[E]]
        case entity: Product       => getDatabaseProduct.asInstanceOf[Array[E]]
        case entity: PurchaseOrder => getDatabasePurchaseOrder.asInstanceOf[Array[E]]
        case _ => {
          logger.error("Entity of type {} not handled by findAll method", entity.getClass.getSimpleName)
          logger.warn("Method will return null")
          null.asInstanceOf[Array[E]]
        }
      }
    }*/
    def persist: E => Unit = { entity =>
      logger.debug("Persisting entity of type {}", entity.getClass.getSimpleName)
      def postAppend(array: Array[E]): Array[E] = {
        array :+ entity
      }
      entity match {
        case entity: CustomerOrder =>
        case entity: Employee      =>
        case entity: Product       =>
        case entity: PurchaseOrder =>
        case _ => {
          logger.error("Entity of type {} not handled by persist method", entity.getClass.getSimpleName)
          logger.warn("Method will return null")
          null.asInstanceOf[Array[E]]
        }
      }
    }
    
    def persistArray: Array[E] => Unit = { entityArray =>
      def postAppendArray(count: Int, array: Array[E]): Array[E] = {
        if (count < entityArray.length) {
          logger.debug("persistArray: start array length: {}", array.length + "")
          val nextArray = array :+ entityArray(count)
          logger.debug("persistArray: end array length: {}", nextArray.length + "")
          postAppendArray(count + 1, nextArray)
        } else {
          array
        }
      }
      val testEntity = entityArray.head
      update
    }
    
    def update: (E, Int) => Unit = { (entity, index) =>
      entity match {
        case entity: CustomerOrder =>
        case entity: Employee      =>
        case entity: Product       =>
        case entity: PurchaseOrder =>
        case _ => {
          logger.error("Entity of type {} not handled by persistArray method", entity.getClass.getSimpleName)
          logger.warn("Method will return null")
        }
      }
    }
  }
}