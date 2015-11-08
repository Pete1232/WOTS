package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class Product(val productId_ :Int,val productName_ :String,val image_ :String,val porousware_ :Boolean)
{
  def this()=this(0,null,null,false)
  
  val productId = new ObjectProperty(this,"productId",productId_)
  val productName = new ObjectProperty(this,"productName",productName_)
  val image = new ObjectProperty(this,"image",image_)
  val porousware = new ObjectProperty(this,"porousware",porousware_)
}