package com.qa.GUI

import scalafx.collections.ObservableBuffer
import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.repositoryimplementations.CustomerOrderRepositoryDummy
import com.qa.repositoryimplementations.EmployeeRepositoryDummy
/**
 * This object contains the logic that retrieves information to display to the user
 * @author pnewman
 */
object Model {
  def getCustomerOrders: ObservableBuffer[CustomerOrder] = {
    val customerOrderBuffer = new ObservableBuffer[CustomerOrder]()
    val repoCO = new CustomerOrderRepositoryDummy
    var customerOrders: Array[CustomerOrder] = repoCO.GenericRepositoryDummy.findAll(new CustomerOrder)
    for (customerOrder <- customerOrders)
      customerOrderBuffer += customerOrder
    customerOrderBuffer
  }

  def validateLogin(user: String, pass: String): Boolean = {
    val repoE = new EmployeeRepositoryDummy
    val employees = repoE.GenericRepositoryDummy.findAll(new Employee)
   
    def checkCredentials(count: Int, employeeList: Array[Employee]): Boolean = {
    if (count >= employees.length)
      false
    val storedUser = employeeList(count).employeeUsername_
    val storedPass = employeeList(count).employeePassword_
      if (storedUser.equals(user) && storedPass.equals(pass))
        true
      else
        checkCredentials(count.+(1), employeeList)
    }
     checkCredentials(0, employees)
  }
}