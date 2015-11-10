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
import com.qa.entities.Product
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.Label
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import scalafx.geometry.Insets
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import com.qa.dummydata.DummyData
import scalafx.geometry.Pos
/**
 * This object contains the logic that determines what should be displayed to the user.
 * @author pnewman
 */
object View{
  val initDummy = DummyData
  val logger = Logger(LoggerFactory.getLogger("View.class"))
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
  def setTrackingScene(orderId:Int,product:Product,count:Int):Scene={
    val scene = new Scene(x,y){
      stylesheets = List(getClass.getResource("WOTS.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = List(createMenu)
        }
/*        left = new VBox{
          children = createTrackingPage(orderId)       
        }*/
        center = new VBox{
          id="tracking"
          children = createTrackingApp(orderId,product,count)
          alignment = Pos.CENTER
        }
      }
    }
    scene
  }
  def setLoginScene:Scene={
    val scene = new Scene(x,y){
      stylesheets = List(getClass.getResource("WOTS.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = createMenu
        }
        center = new VBox{
          children = createLogin
          padding = Insets(80)
          spacing = 20
/*          TODO Work out how to retrieve login credentials from here - low priority
 *          onKeyPressed = {
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
      new Menu("Debug"){
        items = List(
          new MenuItem("Quick Log-In"){
            accelerator = KeyCombination.keyCombination("CTRL+L")
            onAction = {
              e: ActionEvent => Controller.loginDebug
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
    val customerOrderList = Model.getCustomerOrders
    val orderIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Order ID"
      cellValueFactory = {_.value.orderId}
      prefWidth = 160
    }
    val orderStatusCol = new TableColumn[CustomerOrder,String]{
      text = "Order Status"
      cellValueFactory = {_.value.customerOrderStatus}
      prefWidth = 160
    }
    val orderAddressCol = new TableColumn[CustomerOrder,String]{
      text = "Delivery Address"
      cellValueFactory = {_.value.deliveryAddress}
      prefWidth = 160
    }
    val employeeIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Employee ID"
      cellValueFactory = {_.value.employeeId}
      prefWidth = 150
    }
    val claimCol = new TableColumn[CustomerOrder,Int]{
      text = "Claim Order"
      cellValueFactory = {_.value.orderId}
      cellFactory = { (col:TableColumn[CustomerOrder,Int]) => 
        new TableCell[CustomerOrder,Int]{
          item.onChange { (_,_,newOrderId) => 
            graphic = new Button{
              text = "Claim Order "+newOrderId
              onAction = handle(Controller.handleClaim(item.value),Controller.setTracking(item.value))
            }
          }
        }
      }
      prefWidth=160
    }
    val table = new TableView[CustomerOrder](customerOrderList){
      logger.debug("Building customer order table")
      columns ++= List(orderIdCol,orderStatusCol,orderAddressCol,employeeIdCol,claimCol)
    }
    //table.items.update(Model.getCustomerOrders)
    table
  }
  def createNodePO:Node={
    new TextField{
      text = "Hello, World!"
    }
  }
  def createTrackingPage(orderId:Int):List[Node]={
    val productList = Model.getProductByOrderId(orderId)
    val productIdCol = new TableColumn[Product,Int]{
      text = "Product ID"
      cellValueFactory = {_.value.productId}
      prefWidth = 160
    }
    val productNameCol = new TableColumn[Product,String]{
      text = "Product Name"
      cellValueFactory = {_.value.productName}
      prefWidth = 160
    }
    val porouswareCol = new TableColumn[Product,Boolean]{
      text = "Porouswared"
      cellValueFactory = {_.value.porousware}
      prefWidth = 160
    }
    val productTable = new TableView[Product](productList){
      logger.debug("Building product table for customer order {}",orderId+"")
      columns ++= List(productIdCol,productNameCol,porouswareCol)
    }
    List(productTable)
  }
  def createTrackingApp(orderId:Int,product:Product,count:Int):List[Node]={
    val productList = Model.getProductByOrderId(orderId)
    val orderLabel = new Label{
      text = "Order "+orderId
    }
    val productLabel = new Label{
      text = "Current product "+count+" of "+productList.length
    }
    val current = new Label{
      id = "current"
      text = product.aisle_ +""+product.shelf_
    }
    val request = new Label{
      text = "Please collect "+12345+" of "+product.productName_
    }
    val next = new Button{
      text = "Item has been picked"
      onAction = handle(Controller.nextOrder(orderId,productList,count))
    }
    if(count!=0){
    List(orderLabel,productLabel,current,request,next)
    }
    else{
      List(orderLabel,next)
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
      onAction = handle(Controller.handleLogin(user.text.value,pass.text.value))
      onKeyPressed = {
        ke:KeyEvent => {
          logger.debug("Key press detected")
          logger.debug("Detected: "+ke.getCode)
          logger.debug("Enter: "+KeyCode.ENTER)
          if(ke.getCode.toString.equals(KeyCode.ENTER.toString)){
            logger.debug("Enter key press detected")
            Controller.handleLogin(user.text.value,pass.text.value)
          }
        }
      }
    }
    List(label,user,pass,submit)
  }
}