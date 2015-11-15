package com.qa.wotsunit

//TODO Finish tests and create class - low priority

import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder
import com.mongodb.casbah.MongoClient
import com.mongodb.MongoException
import java.sql.DriverManager
import java.sql.SQLException
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext
import com.qa.data.DataConfig
import com.qa.repositories.GenericRepositoryActual
import com.qa.repositories.GenericRepositoryDummy

class DataConfigSpec extends UnitSpec{
  def testEstablishSQLConnection{
    "The establishSQLConnection method" should "return a DriverManager configured for WOTS" in{
      val driverSQL = "com.mysql.jdbc.Driver"
      val urlSQL = "jdbc:mysql://localhost/NBGardens"
      val usernameSQL = "root"
      val passwordSQL = "academy"
      try{
        val conn = DataConfig.establishSQLConnection(urlSQL,usernameSQL,passwordSQL)
        conn.getMetaData.getURL should be (urlSQL)
        conn.getMetaData.getUserName should be (usernameSQL+"@localhost")
      }
      catch{
        case sqle:SQLException => cancel("Test cancelled - Database connection could not be established.")
      }
    }
  }
  
  def testEstablishMongoConnection{
    "The establishMongoConnection method" should "return a MongoDB entity configured for FreshTech" in{
      val mongoClient = MongoClient("localhost",27017)
      val mongoName = "FreshTech"
      try{
        DataConfig.establishMongoConnection(mongoClient,mongoName)
      }
      catch{
        case me:MongoException => cancel("Test cannot run - requested Mongo connection not available")
      }
      val mongoDB = mongoClient(mongoName)      
      DataConfig.establishMongoConnection(DataConfig.mongoClient,DataConfig.mongoName).getName should be (mongoDB.getName)
    }
    it should "return null if no connection is made" in{
      val mongoClient = MongoClient("localhost",27017)
      val databaseFalse = "FailTest"
      DataConfig.establishMongoConnection(mongoClient, databaseFalse) should be (null)
    }
  }
  
  def testCloseSQLConnection{
    "The closeSQLConnection method" should "terminate an active SQL connection" in{
      val driverSQL = "com.mysql.jdbc.Driver"
      val urlSQL = "jdbc:mysql://localhost/NBGardens"
      val usernameSQL = "root"
      val passwordSQL = "academy"
      try{
        val connection = DriverManager.getConnection(urlSQL,usernameSQL,passwordSQL)
        if(connection.isClosed){
          cancel("Test cancelled - Database connection did not open successfully")
        }
        else{
          DataConfig.closeSQLConnection(connection)
          connection.isClosed should be (true)
        }
      }
      catch{
        case sqle:SQLException => cancel("Test cancelled - Database connection could not be established.")
      }
    }
  }
  
  def testConfigureRepositories {
    "The configureRepositories method" should "instantiate entity repositories using actual data if a connection is possible" in{
      if((DataConfig.connectionMongo !== null) & (DataConfig.connectionSQL !== null)){
        DataConfig.configureRepository should be (GenericRepositoryActual)
      }
      else{
        cancel("Test cancelled - no database connection available")
      }
    }
    it should "instantiate the entity repositories with dummy data if a database connection hasn't been established" in{
      if((DataConfig.connectionMongo !== null) & (DataConfig.connectionSQL !== null)){
        cancel("Test cancelled - should not instantiate dummy data if connection is available")
      }
      else{
        DataConfig.configureRepository should be (GenericRepositoryDummy)           
      }
    }
  }
  testConfigureRepositories
  testEstablishMongoConnection
  testEstablishSQLConnection
  testCloseSQLConnection
}