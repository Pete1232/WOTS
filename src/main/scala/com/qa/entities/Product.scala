package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class Product(val productId_ :Int,val productName_ :String,val image_ :String,val porousware_ :Boolean, val orderId_ :Int, val aisle_ :Char, val shelf_ :Int)
{
  def this()=this(0,null,null,false,0,'\u0000',0)
  def this(aisle:Char,shelf:Int)=this(0,null,null,false,0,aisle,shelf)
  
  val productId = new ObjectProperty(this,"productId",productId_)
  val productName = new ObjectProperty(this,"productName",productName_)
  val image = new ObjectProperty(this,"image",image_)
  val porousware = new ObjectProperty(this,"porousware",porousware_)
  val orderId = new ObjectProperty(this,"orderID",orderId_)
  val aisle = new ObjectProperty(this,"aisle",aisle_)
  val shelf = new ObjectProperty(this,"shelf",shelf_)
  
  object Product {
    def getDistance(product:Product):Int = {
      //TODO Rewrite this to account for warehouse layout
      val xDistance = Math.abs(product.aisle_.toInt - aisle_.toInt)
      val yDistance = Math.abs(product.shelf_ - shelf_)
      xDistance+yDistance
    }
  }
}