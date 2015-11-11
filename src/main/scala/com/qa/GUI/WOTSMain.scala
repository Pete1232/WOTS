package com.qa.GUI

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
/**
 * This object contains the main method for the WOTS application
 * @author pnewman
 */
object WOTSMain extends JFXApp{
  stage = new PrimaryStage{
    title = "NBGardens Warehouse Order Tracking System"
    scene = View.setLoginScene
    resizable = false
  }
}