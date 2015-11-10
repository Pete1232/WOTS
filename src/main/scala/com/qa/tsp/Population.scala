package com.qa.tsp

import com.qa.entities.Product

/**
 * @author pnewman
 */
class Population(popSize:Int,stops:List[Product]) {
  val population = createPopulation(0, popSize)
  def createPopulation(count:Int,popSize:Int):List[Tour]={
    val tours:List[Tour]=null
    if(count<popSize){
      val tour = new Tour(stops)
      tour.generateIndividual
      //This may throw an error, try ++= if it does
      tours.updated(count, tour)
      createPopulation(count.+(1),popSize)
    }
    else{
      tours
    }
  }
  def getFittest{
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
    findFittest(0,population(0))
  }
}