package com.qa.data

import com.mongodb.casbah.MongoClient
import com.mongodb.MongoException
import java.sql.DriverManager
import java.sql.SQLException
import com.qa.data.DataConfig
import com.qa.repositories.GenericRepositoryActual
import com.qa.repositories.GenericRepositoryDummy
import com.qa.wotsunit.UnitSpec

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
    "The configureRepositories method" should "instantiate entity repositories using actual data if the parameter online=true" in{
      DataConfig.configureRepository(true) should be (GenericRepositoryActual)
    }
    it should "instantiate the entity repositories with dummy data if the parameter online=false" in{
      DataConfig.configureRepository(false) should be (GenericRepositoryDummy)           
    }
  }
  testConfigureRepositories
  testEstablishMongoConnection
  testEstablishSQLConnection
  testCloseSQLConnection
}