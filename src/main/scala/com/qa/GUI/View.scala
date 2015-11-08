package com.qa.GUI

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.VBox
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.input.KeyCombination
import scalafx.event.ActionEvent
import scalafx.scene.control.TabPane
import scalafx.scene.control.Tab
import scalafx.scene.Node
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableCell
import scalafx.scene.control.Button
import scalafx.scene.control.TableView
import scalafx.scene.control.TextField
import com.qa.entities.CustomerOrder
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.Label
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * This object contains the logic that determines what should be displayed to the user.
 * @author pnewman
 */
object View{
  val logger = Logger(LoggerFactory.getLogger("View.class"))
  val customerOrderList = Model.getCustomerOrders

  def setMainScene:Scene={
    val scene = new Scene(800,450){
      stylesheets = List(getClass.getResource("CustomerOrders.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = List(
            createMenu,
            createNodeCO
          )
        }
      }
    }
    scene
  }
  def setTrackingScene(orderId:Int):Scene={
    val scene = new Scene(800,450){
      root = new BorderPane{
        top = new VBox{
          children = List(
            createMenu,
            createTrackingPage(orderId)
          )
        }
      }
    }
    scene
  }
  def createMenu = new MenuBar{
    menus = List(
      new Menu("Options"){
        items = List(
          new MenuItem("Home"){
            accelerator = KeyCombination.keyCombination("Ctrl+H")
            onAction = {
              e: ActionEvent => Controller.menuHome
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
      text = "Order ID"
      cellValueFactory = {_.value.orderId_}
      prefWidth = 180
    }
    val orderStatusCol = new TableColumn[CustomerOrder,String]{
      text = "Order Status"
      cellValueFactory = {_.value.customerOrderStatus_}
      prefWidth = 180
    }
    val orderAddressCol = new TableColumn[CustomerOrder,String]{
      text = "Delivery Address"
      cellValueFactory = {_.value.deliveryAddress_}
      prefWidth = 180
    }
    val employeeIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Employee ID"
      cellValueFactory = {_.value.employeeId_}
      prefWidth = 180
    }
    val claimCol = new TableColumn[CustomerOrder,Int]{
      text = "Claim Order"
      cellValueFactory = {_.value.orderId_}
      cellFactory = { (col:TableColumn[CustomerOrder,Int]) => 
        new TableCell[CustomerOrder,Int]{
          item.onChange { (_,_,newOrderId) => 
            graphic = new Button{
              text = "Claim Order "+newOrderId
              onAction = {
                e:ActionEvent => Controller.SetScene(e,item.value)
              }
            }
          }
        }
      }
    }
    val table = new TableView[CustomerOrder](customerOrderList){
      columns ++= List(orderIdCol,orderStatusCol,orderAddressCol,employeeIdCol,claimCol)
    }
    table.selectionModel().selectedItem.onChange(
      (_, _, newValue) => println(newValue + " chosen in TableView")
    )
    table
  }
  def createNodePO:Node={
    new TextField{
      text = "Hello, World!"
    }
  }
  def createTrackingPage(orderId:Int):Node={
    new Label{
      text = "Order ID: "+orderId
    }
  }
  /*  Sample code for updating view:
 *  model.customerOrderStatus_.onChange((obs:ObservableValue[String,String], oldValue:String, newValue:String)=>{
    logger.debug("Property customerOrderStatus changed: old value = "+oldValue+", new value = "+newValue)
  })*/
}