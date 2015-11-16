package com.qa.GUI

import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.entities.PurchaseOrder
import com.qa.tsp.Tour
import com.qa.tsp.Population
import com.qa.tsp.Algorithm
import com.qa.entities.CustomerOrderLine
import com.qa.data.DataConfig
import com.qa.entities.CustomerOrderLine
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model {
  val logger = Logger(LoggerFactory.getLogger("Model.object"))
  val repository = DataConfig.repository
  val customerOrders = repository.get(new CustomerOrder)
  val employees = repository.get(new Employee)
  val purchaseOrders = repository.get(new PurchaseOrder)

  /**
   * This method finds all customer orders and returns them in an ObservableBuffer
   */
  def getCustomerOrders(customerOrders: Array[CustomerOrder]): ObservableBuffer[CustomerOrder] = {
    ObservableBuffer[CustomerOrder](customerOrders)
  }
  
  /**
   * This method finds all purchase orders and returns them in an ObservableBuffer
   */  
  def getPurchaseOrders(purchaseOrders:Array[PurchaseOrder]): ObservableBuffer[PurchaseOrder] = {
    ObservableBuffer[PurchaseOrder](purchaseOrders)
  }  
  
  /**
   * This method creates an array of all products corresponding to a given array of order lines
   * @param Array[CustomerOrderLine]
   * @return Array[Product]
   */
  def populateOrder(orderLines:Array[CustomerOrderLine]):Array[Product]={
    def counter(count:Int,products:Array[Product]):Array[Product]={
      if(count<orderLines.length){
        val newProduct = repository.getProductByOrderLine(orderLines(count))
        counter(count.+(1),products:+newProduct)
      }
      else{
        products
      }
    }
    counter(0,Array[Product]())
  }
  
  /**
   *This method carries out a genetic travelling salesman algorithm on a list of products, returning an ordered ObservableBuffer
   * @param Int
   * @param Int
   * @param List[Product]
   * @return ObservableBuffer[Product]
   */
  def calculateRoute(pop: Int, generations: Int, products: List[Product]): ObservableBuffer[Product] = {
    logger.debug("Running travelling salesman with a population of {} over {} generations",pop+"",generations+"")
    val tour = new Tour(products)
    val population = new Population(pop, tour.stops)
    val newPop = Algorithm.evolver(0, generations,population)
    ObservableBuffer[Product](newPop.getFittest.stops)
  }

  /**
   * This method returns information about the current user session based on the given string
   * @param String
   * @param String
   * @return String=>E
   */
  def validateLogin[E](user: String, pass: String): String => E = {
    def checkCredentials(count: Int, employeeList: Array[Employee]): Employee = {
      if (count < employees.length) {
        val storedUser = employeeList(count).employeeUsername_
        val storedPass = employeeList(count).employeePassword_
        if (storedUser.equals(user) && storedPass.equals(pass))
          employeeList(count)
        else
          checkCredentials(count.+(1), employeeList)
      } else {
        logger.debug("Employee not found")
        null
      }
    }
    
    def requestSession(request: String): E = {
      val session = checkCredentials(0, employees)
      if (session != null) {
        request match {
          case "login"            => true.asInstanceOf[E]
          case "employeeId"       => session.employeeId_.asInstanceOf[E]
          case "employeeName"     => session.employeeName_.asInstanceOf[E]
          case "employeeUsername" => session.employeeUsername_.asInstanceOf[E]
          case "employeePassword" => session.employeePassword_.asInstanceOf[E]
          case _                  => null.asInstanceOf[E]
        }
      } else
        false.asInstanceOf[E]
    }
    requestSession
  }
}