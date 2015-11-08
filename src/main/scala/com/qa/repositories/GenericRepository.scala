package com.qa.repositories

/**
 * This trait outlines CRUD methods required by the WOTS application
 * @author pnewman
 */
 trait GenericRepository[E] {
  //TODO Uncomment and implement all methods
  /**
   * This method returns function that returns an array of entities
   * @return f:E=>Array[E]
   */
  def findAll:E=>Array[E]
  /**
   * This method returns a function that persists a single entity
   * @return f:E=>Unit
   */
   def persist:E=>Unit
   /**
    * This method returns a function persists a list of entities
    * @return f:Array[E]=>Unit
    */
   def persistArray:Array[E]=>Unit
  /**
   * This method returns a function that updates (overwrites) a single entity at the given index
   * @return f:(E,Int)=>Unit
   */
  def update:(E,Int)=>Unit
}