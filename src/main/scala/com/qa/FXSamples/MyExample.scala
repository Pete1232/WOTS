package com.qa.FXSamples

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.control.Button
import scalafx.scene.Scene
import scalafx.scene.layout.StackPane

object MyExample extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "My Example"
    width = 800
    height = 600
  }
  val button = new Button
  button.setText("Hello World")
/*  btn.setOnAction(new EventHandler<ActionEvent>(){
      
  }
  )*/
  val root = new StackPane()
  root.getChildren().add(button)
  stage.setScene(new Scene(root,300,300))
  stage.show
}