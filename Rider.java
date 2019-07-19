import java.util.*;
import java.lang.*;

public class Rider {

  private double arrivalTime;
  private int boarding;
  private int destination;
  public Stat stats;

  public Rider(double arrivalTime, int boarding, int destination){
    this.arrivalTime = arrivalTime;
    this.boarding = boarding;
    this.destination = destination;
    BusSim.stopList[boarding].enter(this);
  }

  public double getArrivalTime(){
    return arrivalTime;
  }

  public int getBoarding(){
    return boarding;
  }

  public int getDestination(){
    return destination;
  }

  public Stat getStats(){
    return stats;
  }

  public void displayStats(){
    stats.displayStats();
  }

} // Rider class
