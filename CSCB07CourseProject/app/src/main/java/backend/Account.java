package backend;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * A class that represents an <code> Account </code>.
 * 
 * @author Mandy
 *
 */
public abstract class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private ArrayList<Itinerary> bookedItineraries;

	/**
	 * Creates a new instance of <code> Account </code>.o
	 * 
	 * @param email
	 *            Email belonging to the account holder
	 * @param firstName
	 *            First name of the account holder
	 * @param lastName
	 *            Last name of the account holder
	 * @param password
	 *            Chosen password
	 */
	public Account(String email, String firstName, String lastName, String password) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.bookedItineraries = new ArrayList<Itinerary>();
	}

	/**
	 * Creates a new instance of <code> Account </code>.
	 * 
	 * @param email
	 *            Email belonging to the account holder
	 * @param firstName
	 *            First name of the account holder
	 * @param lastName
	 *            Last name of the account holder
	 */
	public Account(String email, String firstName, String lastName) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * ' Returns account emailtemp.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email to the parameter email.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return the first name of the account holder.
	 * 
	 * @return firstName first name of the account holder.
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * w the first name to the parameter string.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Return the last name of the account holder.
	 * 
	 * @return lastName last name of the account holder
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name to the parameter string.
	 * 
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the itineraries booked by this <code>Account</code>
	 * 
	 * @return bookedItineraries A list of itineraries booked by this
	 *         <code>Account</code>
	 */
	public ArrayList<Itinerary> getBookedItineraries() {
		return this.bookedItineraries;
	}

	/**
	 * Set the list of book itineraries booked by this <code>Account</code>
	 *
	 * @param itineraries
	 *            List of itineraries to replace the booked
	 *            <code>Itinerary</code>;
	 */
	public void setBookedItineraries(ArrayList<Itinerary> itineraries) {
		this.bookedItineraries = itineraries;
	}

	/**
	 * Add a new itinerary to the list of bookedItineraries
	 * 
	 * @param itinerary
	 *            new booking
	 */
	public void addItinerary(Itinerary itinerary) {
		this.bookedItineraries.add(itinerary);
	}

	/**
	 * Remove the itinerary from the list of bookedItineraries
	 * 
	 * @param itinerary
	 *            the itinerary to be removed
	 */

	public void removeItinerary(Itinerary itinerary) {
		this.bookedItineraries.remove(itinerary);
	}

	/**
	 * Returns a list of <code>Itinerary</code> based on the.
	 * 
	 * @param origin
	 *            The starting location
	 * @param destination
	 *            The ending location
	 * @param departureDate
	 *            Day to leave
	 * @return retItineraries Collection of itineraries that are valid of the
	 *         parameter
	 */
	public ResultItinerary searchItinerary(String origin, String destination, Date departureDate, Driver driver) {
		// Return array list
		ArrayList<Itinerary> retItineraries = new ArrayList<Itinerary>();
		// Flight database
		HashMap<Trip, Boolean> tripList = new HashMap<Trip, Boolean>();
		for (Flight flight : driver.getFlightDatabase().getFlights().values()) {
			tripList.put(flight, false);
		}
		// Recursively determine itineraries via helper
		ArrayList<Trip> prevTrips = new ArrayList<Trip>();
		ArrayList<ArrayList<Trip>> goodTrips = new ArrayList<ArrayList<Trip>>();
		goodTrips = searchItineraryHelper(origin, destination, departureDate, tripList, prevTrips);
		// Convert the list of flights into itineraries
		for (ArrayList<Trip> listTrips : goodTrips) {
			retItineraries.add(new Itinerary(listTrips));
		}
		return new ResultItinerary(retItineraries);
	}

	/**
	 * Helper recursive function for retrieving list of itineraries.
	 * 
	 * @param origin
	 *            The starting location
	 * @param destination
	 *            The ending location
	 * @param departureDate
	 *            Day to leave
	 * @param prevTrips
	 *            Collection of flights considered
	 * @return goodTrips List of list of trips that can lead towards the
	 *         destination
	 */
	private ArrayList<ArrayList<Trip>> searchItineraryHelper(String origin, String destination, Date departureDate,
			HashMap<Trip, Boolean> tripList, ArrayList<Trip> prevTrips) {
		ArrayList<Trip> tempPrevTrip = new ArrayList<Trip>(prevTrips);
		ArrayList<ArrayList<Trip>> goodTrips = new ArrayList<ArrayList<Trip>>();
		for (Trip trip : tripList.keySet()) {
			if (trip.getOrigin().equals(origin)
					&& ((!tripList.get(trip) && trip.getDepartureTime().getTime() == departureDate.getTime())
							|| (!tripList.get(trip) && trip.getDepartureTime().getTime() >= departureDate.getTime()))) {
				// Base case: All flights which fit the requirements exactly
				// In this case, add the flight to the previously recorded
				// flights, make that into an itinerary and add to it to the
				// return
				if (trip.getDestination().equals(destination)) {
					tempPrevTrip.add(trip);
					goodTrips.add(tempPrevTrip);
					tripList.put(trip, true);
				} else {
					tripList.put(trip, true);
					goodTrips.addAll(this.searchItineraryHelper(trip.getDestination(), destination,
							trip.getArrivalTime(), tripList, tempPrevTrip));
				}
				tempPrevTrip = new ArrayList<Trip>(prevTrips);
			}
		}
		return goodTrips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  "e-mail = " + email + "," + "\n" +
				"first name = " + firstName + "," + "\n" +
				"last name = " + lastName +  "," + "\n" +
				"password = " + password;
	}

	/**
	 * The password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The change of password.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}