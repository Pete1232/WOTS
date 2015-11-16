package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class CustomerOrderLine (val orderId_ :Int, val productId_ :Int, val quantity_ :Int){
  def this() = this(0,0,0)
  def this(productId:Int) = this(0,productId,0)
  
  val orderId = new ObjectProperty(this,"orderId",orderId_)
  val productId = new ObjectProperty(this,"productId",productId_)
  val quantity = new ObjectProperty(this,"quantity",quantity_)
}