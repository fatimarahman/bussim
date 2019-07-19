public class BusSim {

	public static PQ agenda = new PQ();
	public static double arrivalInterval = 120;
	public static Stop[] stopList = new Stop[30];
	public static Stat riderStats = new Stat();
	public static Stat busStats = new Stat();

	public static void main(String[] args){
		stopList[0] = new Stop("Ramp B", 0);
		stopList[1] = new Stop("4th & Nicollet", 1);
		stopList[2] = new Stop("Anderson Hall", 2);
		stopList[3] = new Stop("Jones Hall", 3);
		stopList[4] = new Stop("Kasota Circle", 4);
		stopList[5] = new Stop("Como & Eustis", 5);
		stopList[6] = new Stop("Como & Cleveland", 6);
		stopList[7] = new Stop("Como & Snelling", 7);
		stopList[8] = new Stop("Como & Hamline", 8);
		stopList[9] = new Stop("Maryland & Dale", 9);
		stopList[10] = new Stop("Maryland & Rice", 10);
		stopList[11] = new Stop("Front & Lexington", 11);
		stopList[12] = new Stop("Front & Dale", 12);
		stopList[13] = new Stop("Capitol", 13);
		stopList[14] = new Stop("Cedar", 14);
		stopList[15] = new Stop("Union Depot", 15);
		stopList[16] = new Stop("Cedar", 16);
		stopList[17] = new Stop("Capitol", 17);
		stopList[18] = new Stop("Front & Dale", 18);
		stopList[19] = new Stop("Front & Lexington", 19);
		stopList[20] = new Stop("Maryland & Rice", 20);
		stopList[21] = new Stop("Maryland & Dale", 21);
		stopList[22] = new Stop("Como & Hamline", 22);
		stopList[23] = new Stop("Como & Snelling", 23);
		stopList[24] = new Stop("Como & Cleveland", 24);
		stopList[25] = new Stop("Como & Eustis", 25);
		stopList[26] = new Stop("Kasota Circle", 26);
		stopList[27] = new Stop("Jones Hall", 27);
		stopList[28] = new Stop("Anderson Hall", 28);
		stopList[29] = new Stop("4th & Nicollet", 29);
		int numOfBuses = 11;
		Bus b1 = new Bus(true, 0); //
		Bus b2 = new Bus(false, 1);
		Bus b3 = new Bus(false, 2);
		Bus b4 = new Bus(false, 3);
		Bus b5 = new Bus(false, 4);
		Bus b6 = new Bus(false, 5);
		Bus b7 = new Bus(false, 6);
		Bus b8 = new Bus(true, 7); //
		Bus b9 = new Bus(false, 8);
		Bus b10 = new Bus(true, 9); //
		Bus b11 = new Bus(false, 10);
		// Bus b12 = new Bus(false, 11);
		// Bus b13 = new Bus(false, 12);
		agenda.add(new BusEvent(0, b1), 0);
		agenda.add(new BusEvent(2, b2), 0);
		agenda.add(new BusEvent(4, b3), 0);
		agenda.add(new BusEvent(8, b4), 0);
		agenda.add(new BusEvent(12, b5), 0);
		agenda.add(new BusEvent(16, b6), 0);
		agenda.add(new BusEvent(20, b7), 0);
		agenda.add(new BusEvent(24, b8), 0);
		agenda.add(new BusEvent(28, b9), 0);
		agenda.add(new BusEvent(2, b10), 0);
		agenda.add(new BusEvent(4, b11), 0);
		// agenda.add(new BusEvent(14, b12), 0);
		// agenda.add(new BusEvent(10, b13), 0);
		agenda.add(new RiderEvent(0), 0);
		agenda.add(new RiderEvent(1), 0);
		agenda.add(new RiderEvent(2), 0);
		agenda.add(new RiderEvent(3), 0);
		agenda.add(new RiderEvent(4), 0);
		agenda.add(new RiderEvent(5), 0);
		agenda.add(new RiderEvent(6), 0);
		agenda.add(new RiderEvent(7), 0);
		agenda.add(new RiderEvent(8), 0);
		agenda.add(new RiderEvent(9), 0);
		agenda.add(new RiderEvent(10), 0);
		agenda.add(new RiderEvent(11), 0);
		agenda.add(new RiderEvent(12), 0);
		agenda.add(new RiderEvent(13), 0);
		agenda.add(new RiderEvent(15), 0);
		agenda.add(new RiderEvent(17), 0);
		agenda.add(new RiderEvent(18), 0);
		agenda.add(new RiderEvent(19), 0);
		agenda.add(new RiderEvent(20), 0);
		agenda.add(new RiderEvent(21), 0);
		agenda.add(new RiderEvent(22), 0);
		agenda.add(new RiderEvent(23), 0);
		agenda.add(new RiderEvent(24), 0);
		agenda.add(new RiderEvent(25), 0);
		agenda.add(new RiderEvent(26), 0);
		agenda.add(new RiderEvent(27), 0);
		agenda.add(new RiderEvent(28), 0);
		agenda.add(new RiderEvent(29), 0);
		while (agenda.getCurrentTime() < 14400){
			agenda.remove().run();
		}
		int totalRiders = b1.totalPassengersBoarded + b2.totalPassengersBoarded +
			b3.totalPassengersBoarded + b4.totalPassengersBoarded +
			b5.totalPassengersBoarded + b6.totalPassengersBoarded +
			b7.totalPassengersBoarded + b8.totalPassengersBoarded +
			b9.totalPassengersBoarded; //+ b10.totalPassengersBoarded; //+
			// b11.totalPassengersBoarded;
		System.out.println(" Total riders boarded: " + totalRiders);
		System.out.println(" Avg. Waiting Time for riders at all stops: " +
			(riderStats.averageWait/riderStats.count)/60 + " min.");
		System.out.println(" Max Waiting Time for riders at all stops: " +
			(riderStats.maxWait)/60 + " min.");
		System.out.println(" Avg & max queue length at each bus stop: ");
		for(int i = 0; i < stopList.length; i++){
			System.out.println("		Stop " + i + ", " + stopList[i].getName() + " ");
			(stopList[i]).getStats().displayStats();
		}
		System.out.println("Average Capacity for Buses: ");
		System.out.println((busStats.averageCapacity)/14400);
	}

} // BusSim
