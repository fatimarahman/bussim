import java.util.*;
import java.lang.*;

// inspired by FerryEvent in CSCI 1933 Lab 10
public class BusEvent implements Event {

  private int currentStop;
  private Bus bus;
  private double timeRemaining;

  public BusEvent (int s, Bus bus){
    this.currentStop = s;
    this.bus = bus;
    this.timeRemaining = 15;
  }

  public void setStop(int s){
    this.currentStop = s;
  }

  public int getStopNumber(){
    return this.currentStop;
  }

  public Bus getBus(){
    return this.bus;
  }

  public void setTimeRemaining(double t){
    this.timeRemaining = t;
  }

  /* bus takes 4 min (240 s) to travel between bus stops
  takes passengers 2 seconds to get off bus, 3 seconds to get on
  bus will either wait at stop for 15 seconds or whatever time it takes for riders
  to get on & off
  */

  public void run(){

    // 1. removes riders who want to exit bus at this stop.
    Rider[] removed = bus.removeRidersAtStop(this.currentStop);
    double time = removed.length*2;

    // 2. boards as many riders who want to go in the bus' direction as possible
    int numberBoarded = 0;
    if (!bus.isExpress()){
      // if the bus isn't express, board all the passengers at the stop
      while (!(this.bus.isFull()) && (BusSim.stopList[currentStop].getQLength() > 0)){
        Rider r = BusSim.stopList[currentStop].getWaitline().remove();
        this.bus.addRider(r);
        BusSim.riderStats.updateWaitTimeStats(BusSim.agenda.getCurrentTime(), r.getArrivalTime());
        numberBoarded++;
      }
    } else {
      // if it is an express bus, it only boards certain passengers
      Q1Gen<Rider> dummy = new Q1Gen<Rider>();
      // ^^ dummy queue for all the passengers who aren't going to express stops
      ArrayList<Rider> toBoard = new ArrayList<Rider>();
      while (!(this.bus.isFull()) && (BusSim.stopList[currentStop].getQLength() > 0)){
        Rider s = (BusSim.stopList[currentStop].getWaitline()).remove();
        int dest = s.getDestination();
        if ((dest%4)==0 || dest == 0 || dest == 1 || dest == 14 || dest == 15 || dest == 29){
          this.bus.addRider(s);
          BusSim.riderStats.updateWaitTimeStats(BusSim.agenda.getCurrentTime(), s.getArrivalTime());
        } else {
          dummy.add(s);
        }
        numberBoarded++;
      }
      // reverse order of dummy node, copy over dummy to actual waitline
      while (dummy.length() > 0) {
        (BusSim.stopList[currentStop]).enter(dummy.remove());
      }
    }

    time += (numberBoarded*3);

    // System.out.println("Bus " + this.bus.getNumber() + " deboarded " + removed.length +
    //   " passengers and boarded " + numberBoarded + " riders at Stop " + this.currentStop +
    //   " for " + time + "seconds.");

    if (time != 0){
      BusSim.agenda.add(this, time);
      //this.bus.updateServiceTimeStats(time);
      this.setTimeRemaining(this.timeRemaining - time);
      return;
    }

    if (this.timeRemaining <= 0){
      BusSim.busStats.updateCapacityStats(BusSim.agenda.getCurrentTime(), this.bus.getCurrentNumOfPassengers());
      if (!bus.isExpress()){
        // if the bus isn't express, just schedule it for the next stop
        this.setStop((this.currentStop+1)%30);
        BusSim.agenda.add(this, 240);
      } else {
        // if the bus is express, you must schedule the next stop differently
        int[] expressStops = {0, 1, 4, 8, 12, 14, 15, 16, 20, 24, 28, 29};
        int idx = 0;
        for (int j = 0; j < expressStops.length; j++)
          if (this.currentStop == expressStops[j]){
            idx = j;
            break;
          }
        this.setStop(expressStops[(idx+1)%expressStops.length]);
        BusSim.agenda.add(this, 240);
        return;
      }
    } else {
      BusSim.agenda.add(this, timeRemaining);
      this.timeRemaining = 0;
    }
  } // run() method

} // BusEvent class
