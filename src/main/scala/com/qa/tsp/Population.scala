package com.qa.tsp

import com.qa.entities.Product

/**
 * @author pnewman
 */
class Population(val popSize:Int,val stops:List[Product]) {
  val population = createPopulation(0, popSize, null)
  def createPopulation(count:Int,popSize:Int,tours:List[Tour]):List[Tour]={
    if(count<popSize){
      val tour = new Tour(stops)
      tour.generateIndividual
      //This may throw an error, try ++= if it does
      val newTour = tours.updated(count, tour)
      createPopulation(count.+(1),popSize,newTour)
    }
    else{
      tours
    }
  }
  def getFittest:Tour={
    def findFittest(count:Int,fittest:Tour):Tour={
      if(count<population.length){
        if(population(count).fitness>fittest.fitness)          
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