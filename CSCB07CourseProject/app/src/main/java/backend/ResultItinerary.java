package backend;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * A class that represents a <code>ResultItinerary</code>.
 * 
 * @author Ciel
 *
 */

public class ResultItinerary implements java.io.Serializable{

  private ArrayList<Itinerary> resItinerary;

  /**
   * Creates a new instance of a <code>ResultItinerary</code>.
   * 
   * @param resItinerary
   *          the list of <code>Itineraries</code>
   */

  public ResultItinerary(ArrayList<Itinerary> resItinerary) {
    this.resItinerary = resItinerary;
  }

  /**
   * Removes a specific Itinerary inside ResultItinerary.
   * 
   * @param itinerary
   *          the itinerary to be removed
   */
  public void removeItinerary(Itinerary itinerary) {
    for (Itinerary i : this.resItinerary) {
      if (i.equals(itinerary)) {
        resItinerary.remove(i);
      }
    }
  }

  /**
   * Sorts the <code>ResultItinerary</code> by cost.
   * 
   * @return the sorted <code>ResultItinerary</code> by cheapest to expensive.
   */
  public ResultItinerary sortByCost() {
    // store all the costs in a new array
    ArrayList<Double> costs = new ArrayList<>();
    for (Itinerary i : this.resItinerary) {
      costs.add(i.getTotalCost());
    }
    // sort that array
    Log.w("path", costs.toString());
    Collections.sort(costs);
    Log.w("path", "after " + costs.toString());

    // match the itineraries with the corresponding costs
    ArrayList<Itinerary> sortedCost = new ArrayList<Itinerary>();
    for (double c : costs) {
      for (Itinerary i : this.resItinerary) {
        Log.w("path", "Here");
        if (i.getTotalCost() == c && !(sortedCost.contains(i))) {
          Log.w("path", "Here1!!!");
          sortedCost.add(i);
        }
      }
    }
    ResultItinerary sorted = new ResultItinerary(sortedCost);
    System.out.println(sorted.toString());
    return sorted;
  }

  /**
   * Sorts the <code>ResultItinerary</code> by cost.
   * 
   * @return the sorted <code>ResultItinerary</code> by least amount of total
   *         time to most.
   */
  public ResultItinerary sortByTime() {
    // store all the times in a new array
    ArrayList<Long> times = new ArrayList<>();
    for (Itinerary i : this.resItinerary) {
      times.add(i.getTotalTime());
    }
    // sort that array
    Collections.sort(times);
    // match the itineraries with the corresponding times
    ArrayList<Itinerary> sortedTime = new ArrayList<Itinerary>();
    for (long t : times) {
      for (Itinerary i : this.resItinerary) {
        if (i.getTotalTime() == t && !sortedTime.contains(i)) {
          sortedTime.add(i);
        }
      }
    }
    ResultItinerary sorted = new ResultItinerary(sortedTime);
    return sorted;
  }

  /**
   * Returns the ArrayList representation of <code>ResultItinerary</code>
   * object.
   * 
   * @return the ArrayList representation of resItinerary
   */
  public ArrayList<Itinerary> getResItinerary() {
    return resItinerary;
  }

  /**
   * Modifies the <code>ResultItinerary</code> object.
   * 
   * @param resItinerary
   *          the resItinerary to set
   */
  public void setResItinerary(ArrayList<Itinerary> resItinerary) {
    this.resItinerary = resItinerary;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return " [resItinerary=" + resItinerary + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ResultItinerary other = (ResultItinerary) obj;
    if (resItinerary == null) {
      if (other.resItinerary != null) {
        return false;
      }
    } else if (!resItinerary.equals(other.resItinerary)) {
      return false;
    }
    return true;
  }

  public static void main(String [] args){
    ArrayList<Trip> t1 = new ArrayList<Trip>();
    Flight f1 = new Flight("", new Date(), new Date(), "", "", "", 200, "");
    Flight f2 = new Flight("", new Date(), new Date(), "", "", "", 200, "");
    t1.add(f1);
    t1.add(f2);
    ArrayList<Trip> t2 = new ArrayList<Trip>();
    Flight f3 = new Flight("", new Date(), new Date(), "", "", "", 200, "");
    Flight f4 = new Flight("", new Date(), new Date(), "", "", "", 200, "");
    t1.add(f3);
    t1.add(f4);
    Itinerary it1 = new Itinerary(t1);
    Itinerary it2 = new Itinerary(t2);

    ArrayList<Itinerary> rit = new ArrayList<>();
    rit.add(it1);
    rit.add(it2);
    ResultItinerary itinerary = new ResultItinerary(new ArrayList<Itinerary>());
  }

}
