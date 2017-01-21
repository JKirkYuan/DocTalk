package backend;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;

/**
 * A class that represents an <code>Doctor</code>.
 * 
 * @author Mandy
 *
 */
public class Doctor extends Account {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of <code> Doctor </code>.
   * 
   * @param email
   *          the unique email of the Doctor, it is used an identifer
   * @param firstName
   *          the first name of the Doctor user
   * @param lastName
   *          the last name of the Doctor user
   * @param password
   *          the password of the Doctor user
   */
  public Doctor(String email, String firstName, String lastName, String password) {
    super(email, firstName, lastName, password);
  }

  /**
   * Given a FlightFile, Doctor can access the file that contains the info of the
   * flight and uploads it to flight database.
   * 
   * @param flightFile
   *          Name of file that contains specific flight
   * 
   */
  public void uploadFlightInfo(String flightFile) {
    // implement later, once we decide how to store the databases
  }

  // /**
  // * Given a Patient, Doctor can view all of the client's info.
  // *
  // * @param client The specific client account the Doctor will view
  // */
  // public List<String> viewData(Patient client) {
  // // Need to discuss with group if we are implementing List<String> or
  // another Object type.
  // List<String> clientData = new ArrayList<String>();
  //
  // String clientEmail = client.getEmail();
  // String firstName = client.getFirstName();
  // String lastName = client.getLastName();
  // String password = client.getPassword();
  // String address = client.getAddress();
  // int age = client.getAge();
  // int creditCardNumber = client.getCreditCardNumber();
  // Date creditCardExpiry = client.getCreditCardExpiry();
  //
  // // Convert creditCardExpiry Date object into String
  // DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
  // String StringcreditCardExpiry = df.format(creditCardExpiry);
  //
  // clientData.add(clientEmail);
  // clientData.add(firstName);
  // clientData.add(lastName);
  // clientData.add(password);
  // clientData.add(address);
  // clientData.add(Integer.toString(age));
  // clientData.add(Integer.toString(creditCardNumber));
  // clientData.add(StringcreditCardExpiry);
  //
  // return clientData;
  //
  // }

}
