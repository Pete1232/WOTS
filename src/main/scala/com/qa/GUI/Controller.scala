package com.qa.GUI

import com.qa.entities.CustomerOrder
import scalafx.event.ActionEvent
/**
 * This object contains the logic that determines which methods in the model and view should
 * be called when certain events are handled.
 * @author pnewman
 */
object Controller {
  def menuHome{
    WOTSMain.stage.scene_=(View.setMainScene)
  }
  def SetScene(e:ActionEvent,orderId:Int){
    WOTSMain.stage.scene_=(View.setTrackingScene(orderId))
  }
}