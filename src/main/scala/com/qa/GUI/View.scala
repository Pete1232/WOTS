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
import scalafx.scene.control.ScrollPane
import com.qa.entities.PurchaseOrder
import scalafx.collections.ObservableBuffer
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
            createMenu
          )
        }
        center = new VBox{
          children = List(createTabs)
        }
      }
    }
    scene
  }
  def setTrackingScene(orderId:Int,product:Product,count:Int,productList:ObservableBuffer[Product]):Scene={
    val scene = new Scene(x,y){
      stylesheets = List(getClass.getResource("WOTS.css").toExternalForm)
      root = new BorderPane{
        top = new VBox{
          children = List(createMenu)
        }
        center = new VBox{
          id="tracking"
          children = createTrackingApp(orderId,product,count,productList)
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
    val customerOrderList = Model.getCustomerOrders(Model.customerOrders)
    val orderIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Order ID"
      cellValueFactory = {_.value.orderId}
    }
    val orderStatusCol = new TableColumn[CustomerOrder,String]{
      text = "Order Status"
      cellValueFactory = {_.value.customerOrderStatus}
    }
    val orderAddressCol = new TableColumn[CustomerOrder,String]{
      text = "Delivery Address"
      cellValueFactory = {_.value.deliveryAddress}
    }
    val employeeIdCol = new TableColumn[CustomerOrder,Int]{
      text = "Employee ID"
      cellValueFactory = {_.value.employeeId}
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
    }
    val table = new TableView[CustomerOrder](customerOrderList){
      logger.debug("Building customer order table")
      columnResizePolicy = TableView.ConstrainedResizePolicy
      columns ++= List(orderIdCol,orderStatusCol,orderAddressCol,employeeIdCol,claimCol)
    }
    table
  }
  def createNodePO:Node={
    val purchaseOrderList = Model.getPurchaseOrders
    val orderIdCol = new TableColumn[PurchaseOrder,Int]{
      text = "Order ID"
      cellValueFactory = {_.value.purchaseOrderId}
    }
    val orderStatusCol = new TableColumn[PurchaseOrder,String]{
      text = "Order Status"
      cellValueFactory = {_.value.purchaseOrderStatus}
    }
    val employeeIdCol = new TableColumn[PurchaseOrder,Int]{
      text = "Employee ID"
      cellValueFactory = {_.value.employeeId}
    }
    val claimCol = new TableColumn[CustomerOrder,Int]{
      text = "Claim Order"
      cellValueFactory = {_.value.orderId}
      cellFactory = { (col:TableColumn[CustomerOrder,Int]) => 
        new TableCell[CustomerOrder,Int]{
          item.onChange { (_,_,newOrderId) => 
            graphic = new Button{
              text = "Claim Order "+newOrderId
              onAction = handle(Controller.handleClaim(item.value), Controller.setHome)
            }
          }
        }
      }
    }
    val table = new TableView[PurchaseOrder](purchaseOrderList){
      logger.debug("Building customer order table")
      columnResizePolicy = TableView.ConstrainedResizePolicy
      columns ++= List(orderIdCol,orderStatusCol,employeeIdCol)
    }
    table
  } 
  def createTrackingApp(orderId:Int,product:Product,count:Int,productList:ObservableBuffer[Product]):List[Node]={
    //val productList = Controller.order //Model.calculateRoute(100, 100, Model.getProductByOrderId(orderId))
    logger.debug(""+productList)
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