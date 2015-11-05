package com.qa.entities

/**
 * @author pnewman
 */
class Product(val productId:Int,val productName:String,val image:String,val porousware:Boolean)
{
  def this()=this(0,null,null,false)
}