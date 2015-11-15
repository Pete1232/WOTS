package com.qa.data

import java.sql.SQLException
import java.sql.Connection
import java.sql.DriverManager
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.MongoDB
import com.mongodb.MongoException
import com.qa.repositories.GenericRepository
import com.qa.repositories.GenericRepositoryDummy
import com.qa.repositories.GenericRepositoryActual

object DataConfig {
  val driverSQL = "com.mysql.jdbc.Driver"
  val urlSQL = "jdbc:mysql://localhost/NBGardens"
  val usernameSQL = "root"
  val passwordSQL = "academy"
  val mongoClient = MongoClient("localhost",27017)
  val mongoName = "FreshTech"
  
  val connectionSQL = establishSQLConnection(urlSQL,usernameSQL,passwordSQL)
  val connectionMongo = establishMongoConnection(mongoClient,mongoName)
  
  def establishSQLConnection(url:String,user:String,pass:String):Connection = {
    try{
      DriverManager.getConnection(urlSQL,usernameSQL,passwordSQL)
    }
    catch{
      case e:SQLException => null
    }
  }
  
  def establishMongoConnection(client:MongoClient,database:String):MongoDB={
    if (client.getDatabaseNames.contains(database)){
      client(database)      
    }
    else{
      null
    }
  }
  
  def closeSQLConnection(conn:Connection):Boolean = {
    try{
      conn.close()
      true
    }
    catch{
      case sqle:SQLException => false
    }
  }
  
  def configureRepository:GenericRepository={
    if(connectionSQL == null || connectionMongo == null){
      GenericRepositoryDummy
    }
    else{
      GenericRepositoryActual
    }
  }
  
}