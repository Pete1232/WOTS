//TODO Try and avoid using this. Use ScalaTest suite instead!

package com.qa.main

import com.qa.repositoryimplementations.ProductRepositoryDummy
import com.qa.entities.Product
import com.qa.repositoryimplementations.EmployeeRepositoryDummy
import com.qa.entities.Employee
/*
 * @author pnewman
 */
object RunTest {
  def main(args:Array[String]){
    val productRepositoryDummy: ProductRepositoryDummy = new ProductRepositoryDummy
    productRepositoryDummy.GenericRepositoryDummy.findAll(new Product)
    for(product<-arrayProduct)
      println("Product! "+product.productId+" -- "+product.productName+" -- "+product.image+" -- "+product.porousware)
    val employeeRepositoryDummy:EmployeeRepositoryDummy = new EmployeeRepositoryDummy
    val arrayEmployee:Array[Employee]=employeeRepositoryDummy.GenericRepositoryDummy.findAll(new Employee)
    for(employee<-arrayEmployee)
      println("Employee! "+employee.employeeId+" -- "+employee.employeeName+" -- "+employee.accessLevel)
  }
}