package com.qa.repositories

import com.qa.entities.CustomerOrderLine
import com.qa.entities.Product

/**
 * This trait outlines CRUD methods to access data for the application
 * @author pnewman
 */
trait GenericRepository{
  
  /**
   * This method returns a data entity of the given type
   * @param E
   * @return Array[E]
   */
  def get[E](entity:E):Array[E]
  
  /**
   * This method should persist a given entity in the corresponding data entity
   * @param E
   */
  def persist[E](entity:E)
  
  /**
   * This method should update an entity at the given index of the corresponding data entity
   * @param E
   * @param Int
   */
  def update[E](entity:E,index:Int)
  
  /**
   * This method should return an array of CustomerOrderLine with the given orderId
   * @param Int
   * @return Array[CustomerOrderLine]
   */
  def getCustomerOrderLineByOrderId(customerOrderId: Int): Array[CustomerOrderLine]
  
  /**
   * This method should return a product corresponding to the given CustomerOrderLine
   * @param CustomerOrderLine
   * @return Product
   */
  def getProductByOrderLine(orderLine:CustomerOrderLine):Product
}