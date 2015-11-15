package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class CustomerOrderLine (val orderId_ :Int, val productId_ :Int, val quantity_ :Int){
  def this() = this(0,0,0)
  
  var orderId = new ObjectProperty(this,"orderId",orderId_)
  var productId = new ObjectProperty(this,"productId",productId_)
  var quantity = new ObjectProperty(this,"quantity",quantity_)
}