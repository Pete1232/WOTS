package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class PurchaseOrder(val purchaseOrderId_ :Int,val employeeId_ :Int,val purchaseOrderStatus_ :String)
{
  def this() = this(0,0,null) 
  
  val purchaseOrderId = new ObjectProperty(this,"purchaseOrderId",purchaseOrderId_)
  val employeeId = new ObjectProperty(this,"employeeId",employeeId_)
  val purchaseOrderStatus = new ObjectProperty(this,"purchaseOrderStatus",purchaseOrderStatus_)
}