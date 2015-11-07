package com.qa.entities

import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

//import java.time.LocalDateTime

/**
 * @author pnewman
 */
class CustomerOrder(val orderId_ :Int,var customerOrderStatus_ :String,val deliveryAddress_ :String)
{
  def this()=this(0,null,null)
  
  val orderId = new ObjectProperty[Int](this,"orderId",orderId_)
  val customerOrderStatus = new StringProperty(this,"customerOrderStatus",customerOrderStatus_)
  val deliveryAddress = new StringProperty(this,"deliveryAddress",deliveryAddress_)  
}