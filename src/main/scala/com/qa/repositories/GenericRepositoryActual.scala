package com.qa.repositories

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
import java.sql.Connection
import java.sql.PreparedStatement
import com.qa.entities.CustomerOrder
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine
import com.qa.entities.CustomerOrderLine
import com.qa.data.DataConfig
import java.sql.SQLException

/**
 * @author pnewman
 */
/*class GenericRepositoryActual[E: Manifest] {
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryMongo.class"))
  val mongoClient = MongoClient("localhost", 27017)
  val mongoDB = mongoClient("FreshTech")
  val driverSQL = "com.mysql.jdbc.Driver"
  val urlSQL = "jdbc:mysql://localhost/NBGardens"
  val usernameSQL = "root"
  val passwordSQL = "academy"*/

  object GenericRepositoryActual extends GenericRepository{
    val logger = Logger(LoggerFactory.getLogger("GenericRepositoryMongo.class"))
    val mongoDB = DataConfig.connectionMongo
    
    def getDatabaseCustomerOrder: Array[CustomerOrder] = {
      val conn = DataConfig.connectionSQL
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
    
    def getDatabasePurchaseOrder: Array[PurchaseOrder] = {
      val conn = DataConfig.connectionSQL
      val stmt = conn.createStatement
      val ps: PreparedStatement = conn.prepareStatement("SELECT * FROM purchaseorder")
      logger.debug("Statement: " + ps)
      val rs = ps.executeQuery
      def createOrders(orders: Array[PurchaseOrder]): Array[PurchaseOrder] = {
        if (rs.next) {
          val orderId = rs.getInt("PurchaseOrderId")
          val employeeId = rs.getInt("Employee_EmployeeId")
          val orderStatus = rs.getString("OrderStatus")
          val newOrder = new PurchaseOrder(orderId,employeeId,orderStatus)
          createOrders(orders :+ newOrder)
        } else {
          orders
        }
      }
      createOrders(Array[PurchaseOrder]())
    }
    
    def getDatabaseEmployee: Array[Employee] = {
      val conn = DataConfig.connectionSQL
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
      val conn = DataConfig.connectionSQL
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
    
    def getProductByOrderLine(orderLine:CustomerOrderLine):Product={
      val productDoc = DataConfig.connectionMongo("Product").find.toArray
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
    
    def persist[E:Manifest]: E => Unit = { entity =>
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
    
    def persistArray[E:Manifest]: Array[E] => Unit = { entityArray =>
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
    
    def update[E:Manifest]: (E, Int) => Unit = { (entity, index) =>
      entity match {
        case entity: CustomerOrder =>
        case entity: Employee      =>
        case entity: Product       =>
        case entity: PurchaseOrder =>
        case _ => {
          logger.error("Entity of type {} not handled by update method", entity.getClass.getSimpleName)
          logger.warn("Method will return null")
        }
      }
    }
  }