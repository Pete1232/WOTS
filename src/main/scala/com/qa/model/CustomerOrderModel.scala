package com.qa.model

import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy

object CustomerOrderModel {
  def getCustomerOrders : ObservableBuffer[CustomerOrder] = {
    val customerOrderBuffer = new ObservableBuffer[CustomerOrder]()
    val repoCO = new CustomerOrderRepositoryDummy
    val customerOrders:Array[CustomerOrder] = repoCO.GenericRepositoryDummy.findAll(new CustomerOrder)
    for(customerOrder <- customerOrders)
      customerOrderBuffer += customerOrder
    customerOrderBuffer
  }
}