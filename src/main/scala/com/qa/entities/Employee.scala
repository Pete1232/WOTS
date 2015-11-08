package com.qa.entities

import scalafx.beans.property.ObjectProperty

/**
 * @author pnewman
 */
class Employee(val employeeId_ :Int,val employeeName_ :String,val employeeUsername_ :String,val employeePassword_ :String,val accessLevel_ :Int)
{
  def this()=this(0,null,null,null,0)  
  
  val employeeId = new ObjectProperty(this,"employeeId",employeeId_)
  val employeeName = new ObjectProperty(this,"employeeName",employeeName_)
  val employeeUsername = new ObjectProperty(this,"employeeUsername",employeeUsername_)  
  val employeePassword = new ObjectProperty(this,"employeeId",employeePassword_)
  val accessLevel = new ObjectProperty(this,"accessLevel",accessLevel_)
}