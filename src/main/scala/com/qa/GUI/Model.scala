package com.qa.GUI

import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
import com.qa.repositoryimplementations.EmployeeRepositoryDummy
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.repositoryimplementations.ProductRepositoryDummy
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model {
  val logger = Logger(LoggerFactory.getLogger("Controller.object"))
  /**
   * This method finds all customer orders and returns them in a suitable format for display (ObservableBuffer)
   */
  def getCustomerOrders: ObservableBuffer[CustomerOrder] = {
    val customerOrderBuffer = new ObservableBuffer[CustomerOrder]
    val repoCO = new CustomerOrderRepositoryDummy
    var customerOrders = repoCO.GenericRepositoryDummy.findAll(new CustomerOrder)
    for (customerOrder <- customerOrders)
      customerOrderBuffer += customerOrder
    customerOrderBuffer
  }
  
  /**
   * This method finds all products associated with the given customer order
   */
  def getProductByOrderId(orderId:Int):ObservableBuffer[Product] = {
    val productBuffer = new ObservableBuffer[Product]
    val repoP = new ProductRepositoryDummy
    val products = repoP.GenericRepositoryDummy.findAll(new Product)
    for(product <- products){
      if(product.orderId_ == orderId){
        logger.debug("Product on order {} found.",orderId+"")
        productBuffer += product
      }
    }
    productBuffer
  }

  /**
   * This method returns a function that returns information about the current user session
   * @param user
   * @param pass
   */
  def validateLogin[E](user: String, pass: String): String =>E = {
    val repoE = new EmployeeRepositoryDummy
    val employees = repoE.GenericRepositoryDummy.findAll(new Employee)
   /**
    * This method checks the provided user and pass against each entity of the employee table until a match is found
    * @param count
    * @param employeeList
    */
    def checkCredentials(count: Int, employeeList: Array[Employee]): Employee = {      
      if (count < employees.length){
        val storedUser = employeeList(count).employeeUsername_
        val storedPass = employeeList(count).employeePassword_
          if (storedUser.equals(user) && storedPass.equals(pass))
            employeeList(count)
          else
            checkCredentials(count.+(1), employeeList)
      }
      else{
        logger.debug("Employee not found")
        null
      }
    }
   /**
    * This method returns session information based on the user request
    * @param request
    */
    def requestSession(request:String):E={  
      val session = checkCredentials(0, employees)
      if(session!=null){
        request match{
          case "login" => true.asInstanceOf[E]
          case "employeeId" => session.employeeId_.asInstanceOf[E]
          case "employeeName" => session.employeeName_.asInstanceOf[E]
          case "employeeUsername" => session.employeeUsername_.asInstanceOf[E]
          case "employeePassword" => session.employeePassword_.asInstanceOf[E]
          case _ => null.asInstanceOf[E]
        }
      }
      else
        false.asInstanceOf[E]
    } 
    requestSession
  }
  def findBy[E](request:String):(Int,E)=>CustomerOrder={
    val repoCO = new CustomerOrderRepositoryDummy
    val orders = repoCO.GenericRepositoryDummy.findAll(new CustomerOrder)
    def findByOrderId(count:Int,orderId:E):CustomerOrder={
      logger.debug("SearchId: {}",orderId.asInstanceOf[Int]+"")
      if(count<orders.length){
        val order = orders(count)
        logger.debug("CurrentId: {}",order.orderId_ +"")
        if(order.orderId_ == orderId)
          order
        else
          findByOrderId(count.+(1),orderId)
      }
      else{
        null
      }
    }
    val findBy:(Int,E)=>CustomerOrder = request match{
      case "orderId" => findByOrderId
      case _ => null
    }
    findBy
  }
}