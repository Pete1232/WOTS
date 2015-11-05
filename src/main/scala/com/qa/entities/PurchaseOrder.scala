package com.qa.entities

/**
 * @author pnewman
 */
class PurchaseOrder(val purchaseOrderId:Int,val employeeId:Int,val purchaseOrderStatus:String)
{
  def this() = this(0,0,null) 
}