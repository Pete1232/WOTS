package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class CustomerOrder(val orderId_ :Int,val customerOrderStatus_ :String,val deliveryAddress_ :String,val employeeID_ :Int)
{
  def this()=this(0,null,null,0)
  
  val orderId = new ObjectProperty(this,"orderId",orderId_)
  val customerOrderStatus = new ObjectProperty(this,"customerOrderStatus",customerOrderStatus_)
  val deliveryAddress = new ObjectProperty(this,"deliveryAddress",deliveryAddress_)  
  val employeeId = new ObjectProperty(this,"employeeId",employeeID_)  
}