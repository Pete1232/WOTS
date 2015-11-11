package com.qa.tsp

import scala.reflect.internal.util.Collections
import scala.util.Random
import com.qa.entities.Product
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
 * @author pnewman
 */
/**
 * @author pnewman
 */
object Tour {
   val logger = Logger(LoggerFactory.getLogger("Tour.object"))
   def generateEmptyTour(count:Int,size:Int,tour:List[Product]) : List[Product]={
      if(count<size){
        val newTour = tour:+(null)
        generateEmptyTour(count.+(1), size, newTour)
      }
      else{
        logger.debug("Empty Tour: "+tour)
        tour
      }
    }
}

class Tour(val stops:List[Product]) {
  val logger = Logger(LoggerFactory.getLogger("Tour.class"))
  def this(size:Int)=this(Tour.generateEmptyTour(0,size,List[Product]()))
  def getStop(tourPosition:Int):Product={
    stops.apply(tourPosition)
  }
  def setStop(tourPosition:Int, product:Product):List[Product]={
    stops.updated(tourPosition, product)
  }
  def getTourDistance:Int={
    def distanceCounter(count:Int, distance:Int):Int={
      logger.debug("Current count: "+count)
      def getThisDistance(endTour:Boolean):Int={
        val currentStop = getStop(count)
        val nextStop = endTour match{
          case false => getStop(count)
          case true => getStop(0)
        }
        logger.debug("Next stop: "+nextStop)
        currentStop.Product.getDistance(nextStop)
      }
      if(count<stops.length-1){
        val thisDistance = getThisDistance(false)
        distanceCounter(count.+(1), distance.+(thisDistance))
      }
      else{
        val thisDistance = getThisDistance(true)
        distance.+(thisDistance)
      }
    }
    distanceCounter(0,0)
  }
  def generateIndividual:List[Product]={
    Random.shuffle(stops)
  }
  def getFitness:Double={
    1/getTourDistance.toDouble
  }
}