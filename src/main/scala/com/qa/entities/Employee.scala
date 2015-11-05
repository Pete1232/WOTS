package com.qa.entities

/**
 * @author pnewman
 */
class Employee(val employeeId:Int,val employeeName:String,val employeeUsername:String,val employeePassword:String,val accessLevel:Int)
{
  def this()=this(0,null,null,null,0)  
}