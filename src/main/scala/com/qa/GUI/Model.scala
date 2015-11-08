package com.qa.GUI

import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model{
  def getCustomerOrders : ObservableBuffer[CustomerOrder] = {
    val customerOrderBuffer = new ObservableBuffer[CustomerOrder]()
    val repoCO = new CustomerOrderRepositoryDummy
    var customerOrders:Array[CustomerOrder] = repoCO.GenericRepositoryDummy.findAll(new CustomerOrder)
    for(customerOrder <- customerOrders)
      customerOrderBuffer += customerOrder
    customerOrderBuffer
  }  
}