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
import scalafx.scene.control._
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.input.KeyCombination
import scalafx.scene.control.TabPane
import scalafx.scene.control.Tab
import scalafx.scene.text.Text
import scalafx.geometry.Insets
import scalafx.scene.control.Button
import scalafx.scene.control.TextField
import scalafx.scene.Node
import scalafx.scene.control.TableColumn
import com.qa.entities.CustomerOrder
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
import com.qa.model.CustomerOrderModel

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
          id = "CO"
          text = "Customer Orders"
          closable = false
          content = createNodeCO
        },
        new Tab{
          id = "PO"
          text = "Purchase Orders"
          closable = false
          content = createNodePO
        }
      )
    }
  }
  def createNodeCO:Node={
    val orderIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Customer Order ID"
      cellValueFactory = {_.value.orderId}
      prefWidth = 180
    }
    val repoCO = new CustomerOrderRepositoryDummy
    val table = new TableView[CustomerOrder](CustomerOrderModel.getCustomerOrders){
      columns += (orderIdCol)
    }
/*    table.selectionModel().selectedItem.onChange(
      (_, _, newValue) => println(newValue + " chosen in TableView")
    )*/
    table
  }
  def createNodePO:Node={
    new TextField
  }
}