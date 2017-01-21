package backend;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Client extends Account implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private String address;
  private String creditCardNumber;
  private Date creditCardExpiry;
  private String creditCardExpiryString;
  private DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
  String ccExpiry;

  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  // parse Date to string
  private String stringCCdate;


  /**
   * Creates a new instance of the Client with the parameter.
   *
   * @param email
   *          Email address of the <code>client</code>
   * @param firstName
   *          First Name of the <code>client</code>
   * @param lastName
   *          Last Name of the <code>client</code>
   * @param address
   *          Address of the <code>client</code>
   * @param creditCardNumber
   *          Credit card # of the <code>client</code>
   * @param creditCardExpiry
   *          Expiration date of teh <code>client</code>'s credit card
   */
  public Client(String email, String firstName, String lastName, String address, String password,
                String creditCardNumber, Date creditCardExpiry) {
    super(email, firstName, lastName, password);
    this.address = address;
    this.creditCardNumber = creditCardNumber;
    this.creditCardExpiry = creditCardExpiry;
    this.stringCCdate = formatter.format(creditCardExpiry);

  }

  public Client(String email, String firstName, String lastName, String address,
                String creditCardNumber, String creditCardExpiryString) {
    super(email, firstName, lastName);
    this.address = address;
    this.creditCardNumber = creditCardNumber;
    this.creditCardExpiryString = creditCardExpiryString;
  }

  /**
   * Given a Itinerary, removes that Itinerary from the itinerary of the Client.
   *
   * @param booking
   *          The Itinerary or flights to be removed from the itinerary
   */
  // this method could also be overloaded to allow for only the ids to delete
  // the flight for more flexibility

    public void cancelItinerary(Itinerary booking) {
      this.getBookedItineraries().remove(booking);
      booking.removeBooking();
    }


  /**
   * Given a Itinerary, books that Itinerary for the client.
   *
   * @param booking
   *          The Itinerary or flights to be booked
   */

    public void purchaseItinerary(Itinerary booking) {
      this.getBookedItineraries().add(booking);
      booking.bookSeat();
    }


  /**
   * Given an Itinerary, calculate the total cost of that Itinerary.
   *
   * @return cost a double value representing the cost
   */

   public double calculateTotalCost(Itinerary itinerary) {
     return itinerary.getTotalCost();
   }


  /**
   * Returns that total cost of all Itineraries booked by the client.
   *
   * @return cost a double value representing the cost
   */

    public double displayTotalCost() {
      double totalCost = 0;
      ArrayList<Itinerary> bookings = this.getBookedItineraries();
      for (Itinerary i : bookings) {
        totalCost += i.getTotalCost();
      }
      return totalCost;
    }


  /**
   * Returns the current mailing address of a customer as a string Some examples
   * of this is L1V 6Y9, or 1500 Queens Street, Toronto.
   *
   * @return the address The mailing address of the client represented by a
   *         String
   */
  public String getAddress() {
    return address;
  }

  /**
   * Set the mailing address of the client.
   *
   * @param address
   *          the address to set The mailing address of the client represented
   *          by a String
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Return the credit card number of the <code>client</code>.
   *
   * @return the creditCardNumber
   */
  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setDateFromString(String date) throws java.text.ParseException{
    Date d = new Date();
    SimpleDateFormat dateformat = new SimpleDateFormat(d.toString());
    this.creditCardExpiry = dateformat.parse(date);
  }

  /**
   * Return the credit card number of the <code>client</code>.
   *
   * @return the creditCardNumber
   */
  public String getCreditCardExpiryString() {
    return creditCardExpiry.toString();
  }




  /**
   * Set the credit card number to the parameter string.
   *
   * @param creditCardNumber
   *          the creditCardNumber to set
   */
  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }
  /**
   * Return the credit card expiration date of the <code>client</code>.
   *
   * @return the creditCardExpiry
   */
  public Date getCreditCardExpiry() {
    return creditCardExpiry;
  }

  /**
   * Set the credit card expiration date to the parameter string.
   *
   * @param creditCardExpiry
   *          the creditCardExpiry to set
   */
  public void setCreditCardExpiry(Date creditCardExpiry) {
    this.creditCardExpiry = creditCardExpiry;
  }

  /**
   * Set the credit card expiration date to the parameter string.
   *
   * @param creditCardExpiry
   *          the creditCardExpiry to set
   */
  public void setCreditCardExpiry(String creditCardExpiry) {
    try {
      this.creditCardExpiry = date.parse(creditCardExpiry);
    } catch (ParseException e) {
      e.printStackTrace();
    }
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
    Client other = (Client) obj;
    if (address == null) {
      if (other.address != null) {
        return false;
      }
    } else if (!address.equals(other.address)) {
      return false;
    }
    if (creditCardExpiry == null) {
      if (other.creditCardExpiry != null) {
        return false;
      }
    } else if (!creditCardExpiry.equals(other.creditCardExpiry)) {
      return false;
    }
    if (creditCardNumber != other.creditCardNumber) {
      return false;
    }
    return true;
  }

  /**
   * This is helper method for parsing the Date into a String. It will be used in the toString()
   *
   * @return the string of credit card expiry date
     */
  public String parseCCexpiry(){
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // parse Date to string
    String Stringdate = formatter.format(this.getCreditCardExpiry());
    return Stringdate;

  }

  @Override
  public String toString() {
    ccExpiry = this.parseCCexpiry();

    return "["  + super.toString() + "," + "\n" +
            "address = " + address + "," + "\n" +
            "credit card number = " + creditCardNumber + "," + "\n" +
            "credit card expiry = " + ccExpiry + "]";
  }

}
