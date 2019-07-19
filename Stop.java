import java.util.*;
import java.lang.*;

public class Stop {

	// contain queue of Riders waiting for bus
	// instantiable for each bus stop

	private Q1Gen<Rider>waitline; // queue of Riders waiting for bus
	private String name;
	private int number;
	private Stat stopStat;

	public Stop(String name, int number){
		this.waitline = new Q1Gen<Rider>(); //general waitline
		this.name = name;
		this.number = number;
		this.stopStat = new Stat();
	}

	// inspired by Washer2 class methods in washer simulation
	public void enter(Rider r){
		// adds a Rider to line of people waiting
		waitline.add(r);
		this.stopStat.updateQueueStats(BusSim.agenda.getCurrentTime(), this.getQLength());
	}

	public int getQLength(){
		// returns number of people waiting in line, general line
		return waitline.length();
	}

	public int getStopNumber(){
		return number;
	}

  public String getName(){
    return this.name;
  }

	public Rider remove(){
		// remove a Rider from the queue
		return (Rider) waitline.remove();
	}

	public Stat getStats(){
		// returns all stats associated with this stop
		return this.stopStat;
	}

	public void printStats(){
		stopStat.displayStats();
	}

	public Q1Gen<Rider> getWaitline(){
		return this.waitline;
	}

	public void setWaitline(Q1Gen<Rider> q1){
		this.waitline = q1;
	}

} //Stop class
