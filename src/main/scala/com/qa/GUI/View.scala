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
import scalafx.geometry.Insets
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
/**
 * This object contains the logic that determines what should be displayed to the user.
 * @author pnewman
 */
object View{
  val logger = Logger(LoggerFactory.getLogger("View.class"))
  val customerOrderList = Model.getCustomerOrders
  val x = 800
  val y = 450

  def setMainScene:Scene={
    val scene = new Scene(x,y){
      stylesheets = List(getClass.getResource("WOTS.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = List(
            createMenu,
            createTabs
          )
        }
      }
    }
    scene
  }
  def setTrackingScene(orderId:Int):Scene={
    val scene = new Scene(x,y){
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
  def setLoginScene:Scene={
    val scene = new Scene(x,y){
      root = new BorderPane{
        top = new VBox{
          children = createMenu
        }
        center = new VBox{
          children = createLogin
          padding = Insets(80)
          spacing = 20
/*          onKeyPressed = {
            ke:KeyEvent => {
              logger.debug("Key press detected")
              logger.debug("Detected: "+ke.getCode)
              logger.debug("Enter: "+KeyCode.ENTER)
              if(ke.getCode.toString.equals(KeyCode.ENTER.toString)){
                logger.debug("Enter key press detected")
              }
            }
          }*/
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
      cellValueFactory = {_.value.orderId}
      prefWidth = 180
    }
    val orderStatusCol = new TableColumn[CustomerOrder,String]{
      text = "Order Status"
      cellValueFactory = {_.value.customerOrderStatus}
      prefWidth = 180
    }
    val orderAddressCol = new TableColumn[CustomerOrder,String]{
      text = "Delivery Address"
      cellValueFactory = {_.value.deliveryAddress}
      prefWidth = 180
    }
    val employeeIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Employee ID"
      cellValueFactory = {_.value.employeeId}
      prefWidth = 180
    }
    val claimCol = new TableColumn[CustomerOrder,Int]{
      text = "Claim Order"
      cellValueFactory = {_.value.orderId}
      cellFactory = { (col:TableColumn[CustomerOrder,Int]) => 
        new TableCell[CustomerOrder,Int]{
          item.onChange { (_,_,newOrderId) => 
            graphic = new Button{
              text = "Claim Order "+newOrderId
              onAction = {
                e:ActionEvent => Controller.SetScene(item.value)
              }
            }
          }
        }
      }
    }
    val table = new TableView[CustomerOrder](customerOrderList){
      logger.debug("Building customer order table")
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
  def createLogin:List[Node]={
    val label = new Label{
        text = "Login"
      }
    val user = new TextField {
      promptText = "Username"
    }
    val pass = new TextField{
      promptText = "Password"
    }
    val submit = new Button{
      text = "Submit"
      onAction = {
        e:ActionEvent => Controller.handleLogin(user.text.toString,pass.text.toString)
      }
      onKeyPressed = {
        ke:KeyEvent => {
          logger.debug("Key press detected")
          logger.debug("Detected: "+ke.getCode)
          logger.debug("Enter: "+KeyCode.ENTER)
          if(ke.getCode.toString.equals(KeyCode.ENTER.toString)){
            logger.debug("Enter key press detected")
            Controller.handleLogin(user.text.toString,pass.text.toString)
          }
        }
      }
    }
    List(label,user,pass,submit)
  }
  /*  Sample code for updating view:
 *  model.customerOrderStatus_.onChange((obs:ObservableValue[String,String], oldValue:String, newValue:String)=>{
    logger.debug("Property customerOrderStatus changed: old value = "+oldValue+", new value = "+newValue)
  })*/
}