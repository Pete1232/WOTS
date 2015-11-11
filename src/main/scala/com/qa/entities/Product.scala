package com.qa.entities

import scalafx.beans.property.ObjectProperty
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV.XDecimal

/**
 * @author pnewman
 */
class Product(val productId_ :Int,val productName_ :String,val image_ :String,val porousware_ :Boolean, val orderId_ :Int, val aisle_ :Char, val shelf_ :Int)
{
  def this()=this(0,null,null,false,0,'\u0000',0)
  def this(aisle:Char,shelf:Int)=this(0,null,null,false,0,aisle,shelf)
  val logger = Logger(LoggerFactory.getLogger("Product.class"))
  val productId = new ObjectProperty(this,"productId",productId_)
  val productName = new ObjectProperty(this,"productName",productName_)
  val image = new ObjectProperty(this,"image",image_)
  val porousware = new ObjectProperty(this,"porousware",porousware_)
  val orderId = new ObjectProperty(this,"orderID",orderId_)
  val aisle = new ObjectProperty(this,"aisle",aisle_)
  val shelf = new ObjectProperty(this,"shelf",shelf_)
  
  object Product {
    def getDistance(product:Product):Int = {
      //TODO Rewrite this to account for warehouse layout
      logger.debug("Calculating distance from aisle {}, shelf {} to aisle {}, shelf {}", ""+aisle_ , ""+shelf_ ,""+product.aisle_ ,""+product.shelf_ )
      val xDistance = Math.abs(product.aisle_.toInt - aisle_.toInt)
      val yDistance = Math.abs(product.shelf_ - shelf_)
      logger.debug("x distance {}, y distance {}",""+xDistance,""+yDistance)
      logger.debug("Added distance {}",xDistance+yDistance+"")
      xDistance+yDistance
    }
  }
}