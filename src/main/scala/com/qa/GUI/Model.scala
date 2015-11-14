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
import com.qa.entities.PurchaseOrder
import com.qa.repositoryimplementations.PurchaseOrderRepositoryDummy
import com.qa.tsp.Tour
import com.qa.tsp.Population
import com.qa.tsp.Algorithm
import com.qa.repositoryimplementations.GenericRepositoryActual
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model {
  val logger = Logger(LoggerFactory.getLogger("Controller.object"))
  val repoCO = /*new GenericRepositoryActual*/new CustomerOrderRepositoryDummy
  val customerOrders = repoCO./*GenericRepositoryActual.getDatabaseCustomerOrder*/GenericRepositoryDummy.findAll(new CustomerOrder)  
  val repoP = new /*GenericRepositoryActual*/ProductRepositoryDummy
  val products = repoP./*GenericRepositoryActual.getDatabaseProduct*/GenericRepositoryDummy.findAll(new Product)

  /**
   * This method finds all customer orders and returns them in a suitable format for display (ObservableBuffer)
   */
  def getCustomerOrders(customerOrders:Array[CustomerOrder]): ObservableBuffer[CustomerOrder] = {
    ObservableBuffer[CustomerOrder](customerOrders)
  }
  
  /**
   * This method finds all products associated with the given customer order
   */
  def getProductByOrderId(orderId:Int,productData:Array[Product]):Array[Product] = {
    val productList = Array[Product]()
    def populateList(count:Int,products:Array[Product]):Array[Product]={
      if(count<productData.length){
        if(productData(count).orderId_ == orderId){
         logger.debug("Product on order {} found.",orderId+"")
         val newProduct = products:+productData(count)
         populateList(count.+(1), newProduct)
        }
        else{
          populateList(count.+(1), products)
        }
      }
      else{
        products
      }
    }
    populateList(0, productList)
  }

  /**
   * 
   */
  def calculateRoute(pop:Int,generations:Int,products:List[Product]):ObservableBuffer[Product]={
    val tour = new Tour(products)
    val population = new Population(pop,tour.stops)
    val newPop = Algorithm.evolver(0, population)
    ObservableBuffer[Product](population.getFittest.stops)
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
/*  TODO Not really useful - maybe remove
 *  def findBy[E](request:String):(Int,E)=>CustomerOrder={
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
  }*/
    def getPurchaseOrders: ObservableBuffer[PurchaseOrder] = {
    val purchaseOrderBuffer = new ObservableBuffer[PurchaseOrder]
    val repoPO = new PurchaseOrderRepositoryDummy
    val purchaseOrders = repoPO.GenericRepositoryDummy.findAll(new PurchaseOrder)    
    for (purchaseOrder <- purchaseOrders)
      purchaseOrderBuffer += purchaseOrder
    purchaseOrderBuffer
  }
}