package com.qa.tsp

import com.qa.entities.Product

import scala.util.Random

/**
 * @author pnewman
 */
object Algorithm {
  val elitism = true
  val mutationRate = 0.015
  val tournamentSize = 5
  
  /**
   * This method 
   */
  def evolvePop(pop:Population)={
    val newPopulation = new Population(pop.popSize,pop.stops)
    if(elitism)
      newPopulation.saveTour(0,pop.getFittest)
    val elitismOffset = 1
    
  }
  def crossover(parent1:Tour,parent2:Tour,pop:Population):Tour={
    val firstChild = new Tour(pop.stops.length)
    val start = (Random.nextDouble*parent1.stops.length).toInt
    val end = (Random.nextDouble*parent2.stops.length).toInt
    def addSubTour(count:Int,child:Tour):Tour={
      if(count<child.stops.length){
        if(start<end && count>start && count<end){
          val newChild = child.setStop(count, parent1.getStop(count))
          addSubTour(count.+(1), new Tour(newChild))
        }
        else if(start>end){
          if(!(count<start && count>end)){
            val newChild = child.setStop(count, parent1.getStop(count))
            addSubTour(count.+(1), new Tour(newChild))
          }
        }
      }
      child
    }
    def completeTour(count:Int,child:Tour):Tour={
      def addStop(count:Int):Tour={
        if(firstChild.stops.apply(count)==null){
          new Tour(firstChild.setStop(count, parent2.getStop(count)))
        }
        else{
          addStop(count.+(1)) 
        }
      }
      if(count<firstChild.stops.length){
        if(!firstChild.stops.contains(parent2.getStop(count))){
          completeTour(count.+(1),addStop(0))
        }
        completeTour(count.+(1),child)
      }
    child
    }
    val secondChild = addSubTour(0, firstChild)
    completeTour(0,secondChild)
  }
  def mutate(tour:Tour){
    def mutateCities(stop1Index:Int){
      if(Random.nextDouble<mutationRate){
        val stop2Index = tour.stops.length*Random.nextDouble.toInt
        val stop1 = tour.getStop(stop1Index)
        val stop2 = tour.getStop(stop2Index)
        tour.setStop(stop2Index, stop1)
        tour.setStop(stop1Index, stop2)
        mutateCities(stop1Index.+(1))
      }
      mutateCities(0)
    }
  }
  def tournamentSelection(pop:Population):Tour={
    val tournament = new Population(tournamentSize,pop.stops)
    def duel(count:Int){
      if(count<pop.stops.length){
        val randomId = (Random.nextDouble*pop.stops.length).toInt
        tournament.saveTour(count, pop.population.apply(randomId))
        duel(count.+(1))
      }
    }
    tournament.getFittest
  }
  def evolver(count:Int,pop:Population):Population={
    if(count<100)
      evolvePop(pop)
    pop
    }
}