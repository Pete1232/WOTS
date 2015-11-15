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

/**
 * This object holds attributes and methods required for database connections
 */
object DataConfig {
  val attemptConnection = false
  
  val driverSQL = "com.mysql.jdbc.Driver"
  val urlSQL = "jdbc:mysql://localhost/NBGardens"
  val usernameSQL = "root"
  val passwordSQL = "academy"
  val mongoClient = MongoClient("localhost",27017)
  val mongoName = "FreshTech"

  val repository = configureRepository(false)
  
  def connectionSQL:Connection={
    establishSQLConnection(urlSQL,usernameSQL,passwordSQL)
  }
  def connectionMongo:MongoDB={
    establishMongoConnection(mongoClient,mongoName)
  }
  
  /**
   * This method attempts to establish a connection to the given SQL database
   * @param String
   * @param String
   * @param String
   * @return Connection
   */
  def establishSQLConnection(url:String,user:String,pass:String):Connection = {
    try{
      DriverManager.getConnection(urlSQL,usernameSQL,passwordSQL)
    }
    catch{
      case e:SQLException => null
    }
  }

  /**
   * This method attempts to establish a connection to the given Mongo database
   * @param MongoClient
   * @param String
   * @return MongoDB
   */
  def establishMongoConnection(client:MongoClient,database:String):MongoDB={
    if (client.getDatabaseNames.contains(database)){
      client(database)      
    }
    else{
      null
    }
  }

  /**
   * This method attempts to close the connection to a given SQL database
   * @param Connection
   * @return Boolean
   */
  def closeSQLConnection(conn:Connection):Boolean = {
    try{
      conn.close()
      true
    }
    catch{
      case sqle:SQLException => false
    }
  }

  /**
   * This method returns the repository implementation corresponding to the database(s) currently in use
   * @return GenericRepository
   */
  def configureRepository(online:Boolean):GenericRepository={
    if(online){
      GenericRepositoryActual
    }
    else{
      GenericRepositoryDummy
    }
  }
  
}