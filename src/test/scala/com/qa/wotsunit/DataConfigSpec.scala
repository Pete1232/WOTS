/*package com.qa.wotsunit

TODO Finish tests and create class - low priority

import com.qa.entities.CustomerOrder
import com.qa.entities.Employee
import com.qa.entities.Product
import com.qa.entities.PurchaseOrder
import com.mongodb.casbah.MongoClient
import com.mongodb.MongoException
import java.sql.DriverManager
import java.sql.SQLException
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext

class DataConfigSpec extends UnitSpec{
  def testEstablishSQLConnection{
    "The establishSQLConnection method" should "return a DriverManager configured for WOTS" in{
      val driverSQL = "com.mysql.jdbc.Driver"
      val urlSQL = "jdbc:mysql://localhost/NBGardens"
      val usernameSQL = "root"
      val passwordSQL = "academy"
      intercept[SQLException]{
        DataConfig.establishSQLConnection(urlSQL,usernameSQL,passwordSQL)
        cancel("Test cannot run - requested SQL connection not available")
      }
      val connection = DriverManager.getConnection(urlSQL,usernameSQL,passwordSQL)
      DataConfig.establishSQLConnection(urlSQL,usernameSQL,passwordSQL) should be (connection)
    }
    it should "throw an SQLException and return null if no connection is made" in{
      intercept[SQLException]{
        val driverSQL = "com.mysql.jdbc.Driver"
        val urlSQL = "jdbc:mysql://localhost/null"
        val usernameSQL = "null"
        val passwordSQL = "null"
        establishSQLConnection(urlSQL,usernameSQL,passwordSQL) should be null
      }
    }
  }
  def testEstablishMongoConnection{
    "The establishMongoConnection method" should "return a MongoDB entity configured for FreshTech" in{
      intercept[MongoException]{
        val mongoClient = MongoClient("localhost",27017)
        DataConfig.establishMongoConnection(mongoClient)
        cancel("Test cannot run - requested Mongo connection not available")
      }
      val mongoClient = MongoClient("localhost",27017)
      val mongoDB = mongoClient("FreshTech")      
      DataConfig.establishMongoConnection(mongoClient) should be (mongoDB)
    }
    it should "throw a MongoException and return null if no connection is made" in{
      intercept[MongoException]{
        val mongoClient = MongoClient("localhost",0)
        establishMongoConnection(mongoClient) should be null
      }
      fail()
    }
  }
  def testConfigureRepositories {
    "The configureRepositories method" should "instantiate entity repositories using actual data" in{
      configureRepository(new CustomerOrder) should be ProductRepositoryDummy
      configureRepository(new Employee) should be ProductRepositoryDummy
      configureRepository(new Product) should be ProductRepositoryDummy
      configureRepository(new PurchaseOrder) should be ProductRepositoryDummy
      
    }
    it should "instantiate the entity repositories with dummy data and log a warning if a database connection hasn't been established" in{
      
    }
    it should "return null and log a warning if an unknown entity is entered" in{
      
    }
  }
  def testDataConfig{
    "Instantiating DataConfig with the no-args constructor" should "run configureRepository for all entities" in{
      
    }
    it should "save these configurations as attributes" in{
      
    }
    it should "save whether actual data is being used with a boolean attribute" in{
      
    }
  }
}*/