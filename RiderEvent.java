public class RiderEvent implements Event {
	/*
	similar to CarMaker
	instantiable for each rider creation event, i.e.
	created for each rider arriving at a bus stop
	will reschedule itself (using agenda), create a Rider, decide
	where they want to go on route, then
	places Rider in correct bus stop queue (how??)
	*/
	private int currentStop;
	private int[] stopSelect = {0, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14,
		15, 15, 16, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 29, 0, 0};
	private double[] arrivalPercents = {0.75, 0.75, 0.5, 0.5, 0.5, 0.2, 0.2, 0.2,
		0.2, 0, 0, -0.2, -0.2, -0.2, -0.5, -0.5, -0.5, -0.75, -0.75};

	public RiderEvent(int current){
		this.currentStop = current;
	}

	private int decide(int low, int high){
		// inspired by CarMaker2 method intRandomInterval in washer simulation
		// chooses a random index from a list
		return (int) Math.floor((high+1-low) * Math.random() + low);
	}


	public double chooseArrivalRate(){
		double x = arrivalPercents[decide(0, (arrivalPercents.length - 1))];
		return (BusSim.arrivalInterval*x) + BusSim.arrivalInterval;
	}

	public int chooseStop(int start){
		// helper function to decide which bus stop a Rider will go to
		// "start" is the variable that represents which stop the Rider got on
		int remove = 0;
		for (int i = 0; i < stopSelect.length; i++){
			if (stopSelect[i] == start){
				remove = i;
				break;
			}
		}
		if (start < 15){
			if (start == 0 || start == 1)
				return stopSelect[decide(remove+2, 18)];
			return stopSelect[decide(remove+1, 18)];
		} else {
			if (start == 29 || start == 14 || start == 15 || start == 16)
				return stopSelect[decide(remove+2, stopSelect.length-1)];
			return stopSelect[decide(remove+1, stopSelect.length-1)];
		}
	}

	public void run(){
		// adds new RiderEvent to agenda
		// average inter-arrival rate of 1 rider at each stop every 120 s
		double time = this.chooseArrivalRate();
		BusSim.agenda.add(new RiderEvent(this.currentStop), time);
		// creates a rider, decides where it wants to go
		Rider newRider = new Rider(BusSim.agenda.getCurrentTime(), this.currentStop,
			chooseStop(this.currentStop));
		// System.out.println("Rider Event Stop: " + this.currentStop + ", Time is: " + BusSim.agenda.getCurrentTime() + ", Next Rider in: " + time);
	}

} // RiderEvent
