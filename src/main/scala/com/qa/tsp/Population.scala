package com.qa.tsp

import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * @author pnewman
 */
object Population {
   val logger = Logger(LoggerFactory.getLogger("Population.object"))
   def generateEmptyPop(count:Int,size:Int,pop:List[Tour]) : List[Tour]={
      if(count<size){
        val newTour = pop:+(null)
        generateEmptyPop(count.+(1), size, newTour)
      }
      else{
        logger.debug("Empty Pop: "+pop)
        pop
      }
    }
}
class Population(val popSize:Int,val stops:List[Product]) {
  val logger = Logger(LoggerFactory.getLogger("Population.class"))
  val population = createPopulation(0, popSize, Population.generateEmptyPop(0,popSize,List[Tour]()))
  def createPopulation(count:Int,popSize:Int,tours:List[Tour]):List[Tour]={
    logger.debug("Entering createPopulation method with count {}, popSize {} and tours {}",count+"",popSize+"",tours)
    if(count<popSize){
      val tour = new Tour(stops)
      val nextTour = new Tour(tour.generateIndividual)
      //This may throw an error, try ++= if it does
      val newTour = tours.updated(count, nextTour)
      createPopulation(count.+(1),popSize,newTour)
    }
    else{
      tours
    }
  }
  def getFittest:Tour={
    logger.debug("Entering getFittest method")
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
  def saveTour(index:Int,tour:Tour):List[Tour]={
    population.updated(index, tour)
  }
}