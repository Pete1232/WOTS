package com.qa.entities

//import java.time.LocalDateTime

/**
 * @author pnewman
 */
class CustomerOrder(val orderId:Int,var customerOrderStatus:String,val deliveryAddress:String)
{
  def this()=this(0,null,null)
}