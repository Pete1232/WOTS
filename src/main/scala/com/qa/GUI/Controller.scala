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
 * This object contains the logic that determines which methods in the model and view should
 * be called when certain events are handled.
 * @author pnewman
 */
object Controller {
  val logger = Logger(LoggerFactory.getLogger("Controller.object"))
  var session = 0
  
  /**
   * This is a convenience method to quickly login from the manu-bar
   * (Would be removed)
   */
  def loginDebug{
    session = -1
    setHome
  }
  
  /**
   * This method sets the view to the home page
   */
  def setHome{
    WOTSMain.stage.scene_=(View.setMainScene)
  }
  
  /**
   * This method sets the view to the tracking page for the given order
   * @param Int
   */
  def setTracking(orderId:Int){
    val orderLines = Model.repository.getCustomerOrderLineByOrderId(orderId)
    val productsOnOrder = Model.populateOrder(orderLines)
    val order = Model.calculateRoute(100, 1000, productsOnOrder.toList) //TODO Replace all Lists with Arrays Model.getProductByOrderId(orderId,Model.products).toList
    WOTSMain.stage.scene_=(View.setTrackingScene(orderId,new Product,0,order))
  }
  
  /**
   * This method saves the session of a valid user after login, and sets the view to the home page
   * @param String
   * @param String
   */
  def handleLogin(user:String,pass:String){
    logger.debug("Attempting login. User is {}. Pass is {}",user,pass)
    if(Model.validateLogin[Boolean](user, pass)("login")){
      session = Model.validateLogin(user, pass)("employeeId")
      setHome
    }
  }
  
  /**
   * This method updates the employee claim on an order to the current session
   * @param Int
   */
  def handleClaim(orderId:Int){
  //TODO Implement
  }
  
  /**
   * This method iterates through the products on an order
   * @param Int
   * @param ObservableBuffer[Product]
   * @param Int
   */
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
}