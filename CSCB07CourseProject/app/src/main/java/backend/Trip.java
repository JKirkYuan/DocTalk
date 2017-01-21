package backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that represents a <code>Trip</code>.
 * 
 * @author Ciel
 *
 */
public abstract class Trip implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Date departureTime;
  private Date arrivalTime;
  private String origin;
  private String destination;
  private int numSeats;
  private double cost;

  /**
   * Creates a new instance of a <code>Trip</code>.
   * 
   * @param departureTime
   *          the time of departure
   * @param arrivalTime
   *          the time of arrival
   * @param origin
   *          the location of departure
   * @param destination
   *          the location of arrival
   * @param cost
   *          the cost of the trip
   * @param numSeats
   * 		  the number of available seats for the trip
   */
  public Trip(Date departureTime, Date arrivalTime, String origin, String destination,
      double cost, String numSeats) {
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.origin = origin;
    this.destination = destination;
    this.cost = cost;
    this.numSeats = Integer.parseInt(numSeats);
  }


  /**
   * Return the cost of the <code>Trip</code>
   * @return the cost
   */
  public double getCost() { return cost; }

  /**
   * Modifies the cost of the <code>Trip</code>
   *
   * @param cost the new cost of this <code>Trip</code>
     */
  public void setCost(double cost){this.cost = cost;}


  /**
   * Returns the departure time of the <code>Trip</code>.
   * 
   * @return the departureTime
   */
  public Date getDepartureTime() {
    return departureTime;
  }

  /**
   * Modifies the departure time of the <code>Trip</code>.
   * 
   * @param departureTime
   *          the departureTime to set
   */
  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Returns the arrival time of the <code>Trip</code>.
   * 
   * @return the arrivalTime
   */
  public Date getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Modifies the arrival time of the <code>Trip</code>.
   * 
   * @param arrivalTime
   *          the arrivalTime to set
   */
  public void setArrivalTime(Date arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  /**
   * Returns the location of departure.
   * 
   * @return the origin
   */
  public String getOrigin() {
    return origin;
  }

  /**
   * Modifies the location of departure.
   * 
   * @param origin
   *          the origin to set
   */
  public void setOrigin(String origin) {
    this.origin = origin;
  }

  /**
   * Returns the destination.
   * 
   * @return the destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Modifies the destination.
   * 
   * @param destination
   *          the destination to set
   */
  public void setDestination(String destination) {
    this.destination = destination;
  }

  /**
   * Gets the number of seats on the flight
   *
   * @return Number of Seats
   * 			A string representing the number of seats on the flight
   *
   */
  public int getNumSeats() {
    return numSeats;
  }

  /**
   * Sets the number of seats on the flight
   *
   * @param numSeats
   * 				Number of seats on the flight to set
   */
  public void setNumSeats(int numSeats) {
    this.numSeats = numSeats;
  }

  /**
   * Add additional seats to this <code>Trip</code>
   *
   * @param newSeats   A number of new seats
     */
  public void addNewSeats(int newSeats){ this.numSeats += newSeats;}

  /**
   * Remove seats upon booking them
   *
   * @param bookedSeats  The number of seats to be booked
   * @return A boolean representing whether the booking was successful
     */
  public boolean bookSeats(int bookedSeats){
    // Only remove the seats if there were be 0 or more seats after the removal
    if(this.getNumSeats() - bookedSeats >= 0){
      this.numSeats -= bookedSeats;
      return true;
    }
    return false;
  }

  /**
   * Returns the duration of the <code>Trip</code>.
   * 
   * @return the tripDuration
   */
  public long getTripDuration() {
    return arrivalTime.getTime() - departureTime.getTime();
  }

  
/*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Trip trip = (Trip) o;

    if (numSeats != trip.numSeats) return false;
    if (this.getTripDuration() != trip.getTripDuration()) return false;
    if (Double.compare(trip.cost, cost) != 0) return false;
    if (departureTime != null ? !departureTime.equals(trip.departureTime) : trip.departureTime != null)
      return false;
    if (arrivalTime != null ? !arrivalTime.equals(trip.arrivalTime) : trip.arrivalTime != null)
      return false;
    if (origin != null ? !origin.equals(trip.origin) : trip.origin != null) return false;
    return destination != null ? destination.equals(trip.destination) : trip.destination == null;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = departureTime != null ? departureTime.hashCode() : 0;
    result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
    result = 31 * result + (origin != null ? origin.hashCode() : 0);
    result = 31 * result + (destination != null ? destination.hashCode() : 0);
    result = 31 * result + numSeats;
    result = 31 * result + (int) (this.getTripDuration() ^ (this.getTripDuration() >>> 32));
    temp = Double.doubleToLongBits(cost);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  /**
   * This is helper method for parsing the Date into a String. It will be used in the toString()
   *
   * @return the string of departureTime
   */
  public String parseDepartureTime(){
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    // parse Date to string
    String departure = formatter.format(this.getDepartureTime());
    return departure;

  }

  /**
   * This is helper method for parsing the Date into a String. It will be used in the toString()
   *
   * @return the string of arrivalTime
   */
  public String parseArrivalTime() {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    // parse Date to string
    String arrival = formatter.format(this.getArrivalTime());
    return arrival;

  }

  public String getHoursMinSec () {

    long tripDuration = this.getTripDuration();
    int seconds = (int) (tripDuration / 1000) % 60 ;
    int minutes = (int) ((tripDuration / (1000*60)) % 60);
    int hours   = (int) ((tripDuration / (1000*60*60)) % 24);

    String stringSec = Integer.toString(seconds);
    String stringMin = Integer.toString(minutes);
    String stringHours = Integer.toString(hours);

    return stringHours + " Hours " + stringMin + " minutes " + stringSec + " seconds";
  }

  @Override
  public String toString() {
    String departure = this.parseDepartureTime();
    String arrival = this.parseArrivalTime();
    return  "departure time = " + departure + "," + "\n" +
            "arrival time = " + arrival + "," + "\n" +
            "origin = " + origin + "," + "\n" +
            "destination = " + destination + "," + "\n" +
            "number of seats = " + numSeats + "," + "\n" +
            "duration = " + this.getHoursMinSec() + "," + "\n" +
            "cost = " + cost;
  }
}
