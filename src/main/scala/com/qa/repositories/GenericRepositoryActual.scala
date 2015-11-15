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
 * This object implements GenericRepository with CRUD methods for SQL and Mongo databases (as applicable)
 * @author pnewman
 */
object GenericRepositoryActual extends GenericRepository{
  val logger = Logger(LoggerFactory.getLogger("GenericRepositoryMongo.class"))
  
  /**
   * This method returns a data array of the given entity type
   * @param E
   * @return Array[E]
   */
  def get[E](entity:E):Array[E]={
    
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
        } 
        else {
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
      def createEmployees(employees: Array[Employee]): Array[Employee] = {
        if (rs.next) {
          val employeeId = rs.getInt("EmployeeId")
          val employeeName = rs.getString("EmployeeName")
          val employeeUsername = rs.getString("EmployeeUsername")
          val employeePassword = rs.getString("EmployeePassword")
          val employeeAccessLevel = rs.getInt("AccessLevel")
          val newEmployee = new Employee(employeeId, employeeName, employeeUsername, employeePassword, employeeAccessLevel)
          createEmployees(employees :+ newEmployee)
        } else {
          employees
        }
      }
      createEmployees(Array[Employee]())
    }
      
    entity match{
      case entity:CustomerOrder => getDatabaseCustomerOrder.asInstanceOf[Array[E]]
      case entity:Employee => getDatabaseEmployee.asInstanceOf[Array[E]]
      case entity:PurchaseOrder => getDatabasePurchaseOrder.asInstanceOf[Array[E]]
      case _ =>{
       logger.error("Entity of type {} not handled by findAll method",entity.getClass.getSimpleName)
       logger.warn("Method will return null")
       null.asInstanceOf[Array[E]]
      }
    }
  }

  /**
   * This method returns an array of CustomerOrderLine with the given orderId
   * @param Int
   * @return Array[CustomerOrderLine]
   */
  def getCustomerOrderLineByOrderId(customerOrderId: Int): Array[CustomerOrderLine] = {
    val conn = DataConfig.connectionSQL
    val stmt = conn.createStatement
    val ps: PreparedStatement = conn.prepareStatement("SELECT * FROM customerorderline WHERE CustomerOrder_CustomerOrderId="+customerOrderId)
    val rs = ps.executeQuery
    def createCustomerOrderLine(orderLines: Array[CustomerOrderLine]): Array[CustomerOrderLine] = {
      if (rs.next) {
        val orderId = rs.getInt("CustomerOrder_CustomerOrderId")
        val productId = rs.getInt("ProductID")
        val quantity = rs.getInt("Quantity")
        val newOrderLine = new CustomerOrderLine(orderId,productId,quantity)
        createCustomerOrderLine(orderLines :+ newOrderLine)
      } else {
        orderLines
      }
    }
    createCustomerOrderLine(Array[CustomerOrderLine]())
  }
  
  /**
   * This method returns a product corresponding to the given CustomerOrderLine
   * @param CustomerOrderLine
   * @return Product
   */
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
        if(count<productDoc.length-1){
          findProduct(count.+(1))
        }
        else{
          null
        }
      }
    }
    findProduct(0)
  }
  
  /**
   * This method persists a given entity in the corresponding database table
   * @param E
   */
    def persist[E](entity: E){
    //TODO Implement
  }

  /**
   * This method updates an entity at the given index of the corresponding database table
   * @param E
   * @param Int
   */
    def update[E](entity:E, index:Int){
    //TODO Implement
  }
}