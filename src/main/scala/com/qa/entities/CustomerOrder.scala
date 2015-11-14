package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class CustomerOrder(val orderId_ :Int,var customerOrderStatus_ :String,val deliveryAddress_ :String,var employeeID_ :Int)
{
  def this()=this(0,null,null,0)
  
  var orderId = new ObjectProperty(this,"orderId",orderId_)
  var customerOrderStatus = new ObjectProperty(this,"customerOrderStatus",customerOrderStatus_)
  var deliveryAddress = new ObjectProperty(this,"deliveryAddress",deliveryAddress_)  
  var employeeId = new ObjectProperty(this,"employeeId",employeeID_)  
}