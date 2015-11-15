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
import com.qa.repositories.GenericRepositoryActual
import com.qa.entities.CustomerOrderLine
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model {
  val logger = Logger(LoggerFactory.getLogger("Model.object"))
  //TODO Dummy/Actual implementation should be chosen automatically
  val customerOrders = GenericRepositoryActual.getDatabaseCustomerOrder
  val employees = GenericRepositoryActual.getDatabaseEmployee
  val purchaseOrders = GenericRepositoryActual.getDatabasePurchaseOrder

  
  def populateOrder(orderLines:Array[CustomerOrderLine]):Array[Product]={
    def counter(count:Int,products:Array[Product]):Array[Product]={
      if(count<orderLines.length){
        val newProduct = GenericRepositoryActual.getProductByOrderLine(orderLines(count))
        counter(count.+(1),products:+newProduct)
      }
      else{
        products
      }
    }
    counter(0,Array[Product]())
  }
  
/*  def populateOrder(orderLines:Array[CustomerOrderLine]):Array[Product]={
    def beep(products:Array[Product],orderLines:Array[CustomerOrderLine]):Array[Product]={
      if(orderLines.isEmpty){
        products
      }
      else{
        val newProduct = GenericRepositoryActual.getProductByOrderLine(orderLines.head)
        beep(products:+newProduct,orderLines.tail)
      }
    }
    beep(Array.empty,orderLines)
}*/
  
  /**
   * This method finds all customer orders and returns them in a suitable format for display (ObservableBuffer)
   */
  def getCustomerOrders(customerOrders: Array[CustomerOrder]): ObservableBuffer[CustomerOrder] = {
    ObservableBuffer[CustomerOrder](customerOrders)
  }

  /**
   * This method finds all products associated with the given customer order
   */
  def getProductByOrderId(orderId: Int, productData: Array[Product]): Array[Product] = {
    val productList = Array[Product]()
    def populateList(count: Int, products: Array[Product]): Array[Product] = {
      if (count < productData.length) {
        if (productData(count).orderId_ == orderId) {
          logger.debug("Product on order {} found.", orderId + "")
          val newProduct = products :+ productData(count)
          populateList(count.+(1), newProduct)
        } else {
          populateList(count.+(1), products)
        }
      } else {
        products
      }
    }
    populateList(0, productList)
  }
  
  def getOrderLineByOrderId(orderId:Int):Array[CustomerOrderLine]={
    GenericRepositoryActual.getDatabaseCustomerOrderLine(orderId)
  }

  def getProductByOrderLine(orderLines:Array[CustomerOrderLine],productData:Array[Product]):Array[Product]={
    def populateProducts(count:Int,productList:Array[Product],orderLine:CustomerOrderLine):Array[Product]={
      if(count<productData.length){
        logger.debug("Order line product Id: {}",""+orderLine.productId_)
        if(productData(count).productId_ == orderLine.productId_){
          val newProduct = productList :+ productData(count)
          populateProducts(count.+(1),newProduct,orderLine)
        }
        else{
          populateProducts(count.+(1),productList,orderLine)
        }
      }
      else{
        productList
      }
    }
    
    def iterateOrderLines(count:Int,productList:Array[Product]):Array[Product]={
      if(count<orderLines.length){
        val productTemp = populateProducts(0, productList, orderLines(count))
        logger.debug("Adding {} products",""+productTemp.length)
        iterateOrderLines(count.+(1), productList ++: productTemp)        
      }
      else{
        productList
      }
    }
    iterateOrderLines(0, Array[Product]())
  }
  
  /**
   *
   */
  def calculateRoute(pop: Int, generations: Int, products: List[Product]): ObservableBuffer[Product] = {
    val tour = new Tour(products)
    val population = new Population(pop, tour.stops)
    val newPop = Algorithm.evolver(0, population)
    ObservableBuffer[Product](population.getFittest.stops)
  }

  /**
   * This method returns a function that returns information about the current user session
   * @param user
   * @param pass
   */
  def validateLogin[E](user: String, pass: String): String => E = {
    /**
     * This method checks the provided user and pass against each entity of the employee table until a match is found
     * @param count
     * @param employeeList
     */
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

    /**
     * This method returns session information based on the user request
     * @param request
     */
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

  def getPurchaseOrders(purchaseOrders:Array[PurchaseOrder]): ObservableBuffer[PurchaseOrder] = {
    ObservableBuffer[PurchaseOrder](purchaseOrders)
  }
}