package com.qa.GUI

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.ScrollPane
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.scene.layout.FlowPane
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.MenuBar
import scalafx.scene.layout.VBox
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.input.KeyCombination
import scalafx.scene.control.TabPane
import scalafx.scene.control.Tab
import scalafx.scene.text.Text
import scalafx.geometry.Insets

/**
 * @author pnewman
 */
object CustomerOrders extends JFXApp{
  val textRef = new Text{
    text = "Hello, World!"
  }
  stage = new PrimaryStage{
    title = "Hello"
    scene = new Scene(800,450){
      stylesheets = List(getClass.getResource("CustomerOrders.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = List(
            createMenu,
            createTabs
          )
        }
      }
    }
  }
  def createMenu = new MenuBar{
    menus = List(
      new Menu("File"){
        items = List(
          new MenuItem("Something useful..."){
            accelerator = KeyCombination.keyCombination("Ctrl+N")
            onAction = {
              e: ActionEvent => println(e.eventType + "on MenuItem New")
            }
          }
        )
      }
    )
  }
  def createTabs:TabPane = {
    new TabPane{
      tabs = List(
        new Tab{
          text = "Customer Orders"
          closable = false
        },
        new Tab{
          text = "Purchase Orders"
          closable = false
        }
      )
    }
  }
}