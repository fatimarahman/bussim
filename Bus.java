import java.util.*;
import java.lang.*;

// inspired by Ferry class in Lab 10
public class Bus {

	private ArrayList<Rider>lst = new ArrayList<Rider>();
	private double arrivalTime;
	private int max = 50; // holds 50 passengers max
	private boolean express;
	public int totalPassengersBoarded;
	public Stat myStats;
	private int number; // out of all the buses, what number bus is this?
	public double averageCapacity;
	public double oldCapacity;
	public double lastCapacityUpdateTime;

	public Bus(boolean ex, int num){
		this.express = ex;
		this.number = num;
	}

	public boolean addRider(Rider r){
		if (lst.size() < max){
			lst.add(r);
			totalPassengersBoarded++;
			// this.stats.updateCapacityStats(BusSim.agenda.getCurrentTime(),
			// 	this.getCurrentNumOfPassengers());
			return true;
		} return false;
	}

	public Rider[] removeRidersAtStop(int stopNum){
		Rider[] dummy = new Rider[50];
		int index = 0;
		for (int i = 0; i < this.lst.size(); i++){
			if ((lst.get(i)).getDestination() == stopNum){
				dummy[index] = lst.get(i);
				index++;
				lst.remove(i);
			}
		}
		int cutoff = index;
		// now copy over all non-null elements to non-dummy array
		Rider[] removed = new Rider[cutoff];
		System.arraycopy(dummy, 0, removed, 0, cutoff);
		return removed;
	}

	public boolean isFull(){
		return (this.lst.size() == this.max);
	}

	public boolean isExpress(){
		return this.express;
	}

	public void setArrivalTime(double t){
		this.arrivalTime = t;
	}

	public int getNumber(){
		return this.number;
	}

	public Stat getStats(){
		return this.myStats;
	}

	public int getCurrentNumOfPassengers(){
		// how many passengers are currently on the bus
		return this.lst.size();
	}

} // Bus
