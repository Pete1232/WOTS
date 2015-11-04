package com.qa.entities

/**
 * @author pnewman
 */
class Product(
  val productId : Int,
  val productName : String,
  var stockLevel : Int,
  var price : Float,
  val category : String,
  val productDescription : String,
  var rating : Int,
  var image : String,
  var onOffer : Boolean,
  var offerPrice : Float,
  var discontinued : Boolean,
  val porousware : Boolean
  )
{
  def this(productId:Int,productName:String,stockLevel:Int,price:Float,category:String,productDescription:String,image:String,porousware:Boolean)=
    this(productId,productName,stockLevel,price,category,productDescription,0,image,false,0,false,porousware)
}