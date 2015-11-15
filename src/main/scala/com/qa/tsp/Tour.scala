package com.qa.tsp

import scala.reflect.internal.util.Collections
import scala.util.Random
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object Tour {
   def generateEmptyTour(count:Int,size:Int,tour:List[Product]) : List[Product]={
      if(count<size){
        val newTour = tour:+(null)
        generateEmptyTour(count.+(1), size, newTour)
      }
      else{
        tour
      }
    }
}

/**
 * This class creates a 'Tour' of products and relevant methods
 * @author pnewman
 */
class Tour(val stops:List[Product]) {
  val logger = Logger(LoggerFactory.getLogger("Tour.class"))
  def this(size:Int)=this(Tour.generateEmptyTour(0,size,List[Product]()))
  
  /**
   * This method returns a product at the given position in the tour
   * @param Int
   * @return Product
   */
  def getStop(tourPosition:Int):Product={
    stops.apply(tourPosition)
  }
  
  /**
   * This method sets the Product at a given location in the Tour and returns the Tour as a list of products
   * @param Int
   * @param Product
   * @return List[Product]
   */
  def setStop(tourPosition:Int, product:Product):List[Product]={
    stops.updated(tourPosition, product)
  }
  
  /**
   * This method returns the total length of the tour
   * @return Int
   */
  def getTourDistance:Int={
    def distanceCounter(count:Int, distance:Int):Int={
      def getThisDistance(endTour:Boolean):Int={
        val currentStop = getStop(count)
        val nextStop = endTour match{
          case false => getStop(count+1)
          case true => getStop(0)
        }
        val result = currentStop.Product.getDistance(nextStop)
        result
      }
      if(count<stops.length-1){
        val thisDistance = getThisDistance(false)
        distanceCounter(count.+(1), distance+thisDistance)
      }
      else{
        val thisDistance = getThisDistance(true)
        val finalCount = distance.+(thisDistance)
        finalCount
      }
    }
    distanceCounter(0,0)
  }
  
  /**
   * This method returns a randomised copy of the tour as a list of products
   * @return List[Product]
   */
  def generateIndividual:List[Product]={
    Random.shuffle(stops)
  }
  
  /**
   * This method returns the 'Fitness' of the tour. The higher the fitness the shorter the total distance
   * @return Double
   */
  def getFitness:Double={
    1/getTourDistance.toDouble
  }
}