package com.qa.GUI

import com.qa.entities.CustomerOrder
import com.qa.entities.Product
import scalafx.event.ActionEvent
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.qa.data.DummyData
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
/**
 * TODO rewrite the methods when all completed
 * This object contains the logic that determines which methods in the model and view should
 * be called when certain events are handled.
 * @author pnewman
 */
object Controller {
  val logger = Logger(LoggerFactory.getLogger("Controller.object"))
  var session = 0
  var order:ObservableBuffer[Product] = null
  def loginDebug{
    session = -1
    setHome
  }
  def setHome{
    WOTSMain.stage.scene_=(View.setMainScene)
  }
  def setTracking(orderId:Int){
    //val productDB =  Model.getProducts
    val orderLines = Model.getOrderLineByOrderId(orderId)
    logger.debug("Order Lines: "+orderLines.length)
    val productsOnOrder = Model.populateOrder(orderLines)
    logger.debug("Products on order: "+productsOnOrder.length)
    val order = Model.calculateRoute(10, 1000, productsOnOrder.toList) //TODO Replace all Lists with Arrays Model.getProductByOrderId(orderId,Model.products).toList
    WOTSMain.stage.scene_=(View.setTrackingScene(orderId,new Product,0,order))
  }
  def handleLogin(user:String,pass:String){
    logger.debug("Attempting login. User is {}. Pass is {}",user,pass)
    logger.debug("Login: "+Model.validateLogin(user, pass)("login"))
    if(Model.validateLogin[Boolean](user, pass)("login")){
      session = Model.validateLogin(user, pass)("employeeId")
      setHome
    }
  }
  def handleClaim(orderId:Int){
/*    logger.debug("Claiming order {}",orderId+"")
    val order = Model.getProductByOrderId(orderId,Model.products)
    logger.debug("Found order: {}",order)
    logger.debug("Employee Id for {}: {}",orderId+"",DummyData.databaseCustomerOrder.databaseArray(orderId-1).employeeId)
    logger.debug("Session: "+session)
    //TODO Generic update method
    //DummyData.databaseCustomerOrder.databaseArray(orderId-1).employeeId = new ObjectProperty(new CustomerOrder,"employeeId",session)
    logger.debug("New Employee Id: "+DummyData.databaseCustomerOrder.databaseArray(orderId-1).employeeId)
    logger.debug("DummyData customerOrder {} has employeeId {}",orderId+"",DummyData.databaseCustomerOrder.databaseArray(orderId-1).employeeId)*/
  }
  def nextOrder(orderId:Int,productList:ObservableBuffer[Product],count:Int){
    if(count >= productList.length){
      logger.debug("End of buffer")
      WOTSMain.stage.scene_=(View.setMainScene)
    }
    else{
    logger.debug("Product in buffer: {}, of index {}",productList(count),count+"")
    WOTSMain.stage.scene_=(View.setTrackingScene(orderId,productList(count),count.+(1),productList))
    }
  }
/*  def getProductList(orderId:Int):ObservableBuffer[Product]={
    Model.calculateRoute(1000, 1000, Model.getProductByOrderId(orderId,Model.products).toList)//TODO Replace all Lists with Arrays
  }*/
}