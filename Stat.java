public class Stat {
  // inspired by Washer/Car simulation code from CSCI 1933

  // private variables
  public int index;
  public double lastUpdateTime;
  public int oldQLength;
  public double lastQUpdateTime;
  public int count;
  public double totalTime;
  // public double busyTime;
  // public double idleTime;
  public double maxWait;
  public double averageWait;
  public int maxQLength;
  public double averageQLength;
  public double averageServiceTime;
  // public double capacity;
  public double averageCapacity;
  public double oldCapacity;
  public double lastCapacityUpdateTime;

  public Stat(){
    // index = n;
  }

  public double getAverageQLength(){
    return averageQLength;
  }

  public void updateQueueStats(double time, int qlen){
    if (qlen > maxQLength)
      maxQLength = qlen;
    averageQLength += oldQLength * (time - lastQUpdateTime);
    totalTime += time - lastQUpdateTime;
    lastQUpdateTime = time;
    oldQLength = qlen;
  }

  // public void updateBusyTimeStats(double time) {
  //   busyTime += time - lastUpdateTime;
  //   lastUpdateTime = time;
  // }

  // public void updateIdleTimeStats(double time) {
  //   idleTime += time - lastUpdateTime;
  //   lastUpdateTime = time;
  // }

  public void updateServiceTimeStats(double st) {
    averageServiceTime += st;
  }

  public void updateWaitTimeStats(double time, double enterTime) {
    double wait = time - enterTime;
    if (wait > maxWait)
      maxWait = wait;
    averageWait += wait;
    count++;  // another leaves the waiting queue
  }

  public void updateCapacityStats(double time, double capacity){
    averageCapacity += oldCapacity* (time - lastCapacityUpdateTime);
    // totalTime += time - lastCapacityUpdateTime;
    lastCapacityUpdateTime = time;
    oldCapacity = capacity;
  }

  public void displayStats() {
    // System.out.println("\n Bus: " + index + "    ");
    // System.out.println("\n** Simulation Results **\n");
    // System.out.println(" Calculated Simulation Time: " + totalTime);
    // System.out.println(" Idle Time: " + idleTime);
    // System.out.println(" Busy Time: " + busyTime);
    // System.out.println(" (Busy Time based on service time: " +
    //                                        averageServiceTime + ")\n");
    System.out.println(" Maximum Queue Length: " + maxQLength);
    System.out.println(" Avg. Queue Length: " + averageQLength/totalTime);
  //   System.out.println(" Maximum Waiting Time: " + maxWait);
    // System.out.println(" Avg. Waiting Time for riders at all stops: " +
    //                                          (averageWait/count)/60 + " min.");
  //   System.out.println(" Avg. Service Time: " + averageServiceTime/count);
  //   System.out.println(" Number of cars through system: " + count);
  //   System.out.println("\n");
  }  // displayStats

}
