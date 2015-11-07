package com.qa.entities

import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

//import java.time.LocalDateTime

/**
 * @author pnewman
 */
class CustomerOrder(val orderId :Int,var customerOrderStatus :String,val deliveryAddress :String,var employeeID :Int)
{
  def this()=this(0,null,null,0)
  
  val orderId_ = new ObjectProperty[Int](this,"orderId",orderId)
  val customerOrderStatus_ = new StringProperty(this,"customerOrderStatus",customerOrderStatus)
  val deliveryAddress_ = new StringProperty(this,"deliveryAddress",deliveryAddress)  
  val employeeId_ = new ObjectProperty[Int](this,"employeeId",employeeID)  
}