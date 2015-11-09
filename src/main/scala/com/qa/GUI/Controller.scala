package com.qa.GUI

import com.qa.entities.CustomerOrder
import scalafx.event.ActionEvent
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
/**
 * TODO rewrite the methods when all completed
 * This object contains the logic that determines which methods in the model and view should
 * be called when certain events are handled.
 * @author pnewman
 */
object Controller {
  val logger = Logger(LoggerFactory.getLogger("Controller.object"))
  var session = 0
  def setHome{
    WOTSMain.stage.scene_=(View.setMainScene)
  }
  def setTracking(orderId:Int){
    WOTSMain.stage.scene_=(View.setTrackingScene(orderId))
  }
  def handleLogin(user:String,pass:String){
    logger.debug("Attempting login. Useris {}. Pass is {}",user,pass)
    logger.debug("Login: "+Model.validateLogin(user, pass)("login"))
    if(Model.validateLogin[Boolean](user, pass)("login")){
      session = Model.validateLogin(user, pass)("employeeId")
      setHome
    }
  }
  def handleClaim(orderId:Int){
    logger.debug("Claiming order {}",orderId+"")
    val order = Model.findBy("orderId")(0,orderId)
    logger.debug("Found order: {}",order)
    order.employeeID_ = session
  }
}