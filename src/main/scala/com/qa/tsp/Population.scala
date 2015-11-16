package com.qa.tsp

import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object Population {
   val logger = Logger(LoggerFactory.getLogger("Population.object"))
   
   /**
    * This method generates a population of null tours of the given size
    * @param size
    */
   def generateEmptyPop(size:Int):Array[Tour]={
     def createEmptyPopulation(count:Int,pop:Array[Tour]) : Array[Tour]={
        if(count<size){
          val newTour = pop:+(null)
          createEmptyPopulation(count.+(1), newTour)
        }
        else{
          pop
        }
      }
      createEmptyPopulation(0,Array[Tour]())
    }
}

/**
 * This class contains a population of tours
 * @author pnewman
 */
class Population(val popSize:Int,val stops:List[Product]) {
  val logger = Logger(LoggerFactory.getLogger("Population.class"))
  var population = createPopulation(0, popSize, Population.generateEmptyPop(popSize))
  
  /**
   * This method creates a population of tours for the given list of stops (products)
   * @param Int
   * @param Int
   * @param Array[Tour]
   * @return Array[Tour]
   */
  def createPopulation(count:Int,popSize:Int,tours:Array[Tour]):Array[Tour]={
    if(count<popSize){
      val tour = new Tour(stops)
      val nextTour = new Tour(tour.generateIndividual)
      val newTour = tours.updated(count, nextTour)
      createPopulation(count.+(1),popSize,newTour)
    }
    else{
      tours
    }
  }
  
  /**
   * This method returns the shortest route of all tours in the population
   * @return Tour
   */
  def getFittest:Tour={
    def findFittest(count:Int,fittest:Tour):Tour={
      if(count<population.length){
        if(population(count).getFitness>fittest.getFitness)          
          findFittest(count.+(1),population(count))
        else
          findFittest(count.+(1),fittest)
      }
      else
        fittest
    }
    findFittest(0,population.apply(0))
  }
  
  /**
   * This method updates the tour at the given index
   * @param Int
   * @param Tour
   * @return Array[Tour]
   */
  def saveTour(index:Int,tour:Tour){
    population = population.updated(index, tour)
  }
}