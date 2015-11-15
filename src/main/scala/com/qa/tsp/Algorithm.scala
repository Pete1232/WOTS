package com.qa.tsp

import com.qa.entities.Product

import scala.util.Random

/**
 * This object contains methods to execute a genetic travelling salesman algorithm
 * @author pnewman
 */
object Algorithm {
  val mutationRate = 0.015
  val tournamentSize = 5

  /**
   * This method 'evolves' a population for a single generation
   * @param Population
   */
  def evolvePop(pop:Population){
    val population = new Population(pop.popSize,pop.stops)
    population.saveTour(0,pop.getFittest)
    def cross(count:Int){
      if(count<population.popSize){
        val parent1 = tournamentSelection(population)
        val parent2 = tournamentSelection(population)
        val child = crossover(parent1, parent2, population)
        population.saveTour(count, child)
        cross(count.+(1))
      }
    }
    def mutator(count:Int){
      if(count<population.popSize){
        val newTour = mutate(population.population(count))
        population.saveTour(count, newTour)
        mutator(count.+(1))
      }
    cross(0)
    mutator(0)
    }
  }
  
  /**
   * This method crosses two parent tours together by taking a sub-tour of one and adding remaining stops in order from the other tour
   * @param Tour
   * @param Tour
   * @param Population
   */
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
  
  /**
   * This method randomly rearranges the stops in a tour
   */
  def mutate(tour:Tour):Tour={
    def mutateCities(stop1Index:Int):Tour={
      if(Random.nextDouble<mutationRate){
        val stop2Index = tour.stops.length*Random.nextDouble.toInt
        val stop1 = tour.getStop(stop1Index)
        val stop2 = tour.getStop(stop2Index)
        tour.setStop(stop2Index, stop1)
        tour.setStop(stop1Index, stop2)
        mutateCities(stop1Index.+(1))
      }
      else{
        tour
      }
    }
    mutateCities(0)
  }
  
  /**
   * This method chooses the best tour from a relatively small list (default 5) as candidate for crossover
   * @param Population
   */
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
  
  /**
   * This method evolves the population over a given number of generations
   * @param Int
   * @param Int
   * @param Population
   */
  def evolver(count:Int,generations:Int,pop:Population):Population={
    if(count<generations){
      evolvePop(pop)
      evolver(count.+(1),generations,pop)
    }
    else{
      pop
    }
  }
}