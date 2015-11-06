package com.qa.repositories

/**
 * @author pnewman
 */
 trait GenericRepository[E] {
  //TODO Uncomment and implement all methods
  /**
   * This method returns function that returns an array of entities
   * @return
   */
  def findAll:E=>Array[E]
  /**
   * This method persists a list of entities
   * @param entities
   */
   def persist:E=>Unit
   def persistArray:Array[E]=>Unit
/*  *//**
   * This method updates (overwrites) a list of entities in a table
   * @param entities
   *//*
  def update(entities:Array[E])
  *//**
   * This method removes a list of entities in a table
   * @param entities
   *//*
   def remove(entities:Array[E])
  *//**
   * This method returns a single entity with the given ID
   * @param entityID
   *//*
   def findByID(entityId:Int):E 
     *//**
   * This method returns a list of entities with the given Name
   * @param entityName
   *//*
   def findByID(entityName:String):Array[E]*/
}