package com.qa.SFXSamples

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.control.Button
import scalafx.scene.Scene
import scalafx.scene.layout.StackPane
import scalafx.event.EventHandler
import scalafx.event.ActionEvent
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.GridPane
import scalafx.geometry.Pos
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

object MyExample extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "ScalaFX Example"
    width = 800
    height = 600
  }
  val button = new Button
  button.setText("Say Hello World")
  button.handleEvent(MouseEvent.Any){
    me:MouseEvent =>{
      me.eventType match{
        case MouseEvent.MousePressed =>{
          println("Hello, World!")
        }
        case _ =>{}
      }
    }
  }
  //Scala style!
  val hbox = new HBox{
    layoutX = 60
    children = List{
      new Button{
        text="Another Button!?"
        onAction=handle{println("Another message for another button!")}
      }
    }
  }
  //Java-esque :(
  val grid = new GridPane()
  grid.setAlignment(Pos.CENTER)
  grid.setHgap(10)
  grid.setVgap(10)
  grid.setPadding(Insets(5))
  grid.add(hbox,0,0)
  grid.add(button,3,3)
  stage.setScene(new Scene(grid,300,300))
  stage.show
}