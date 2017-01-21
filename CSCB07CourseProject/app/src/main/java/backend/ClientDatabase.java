package backend;

import android.Manifest;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * A Class that represent a <code>ClientDatabase</code>.
 * 
 * @author Ciel
 *
 */
public class ClientDatabase extends Database implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private String filepath;
  private HashMap<String, Patient> clients;

  private static final int REQUEST_EXTERNAL_STORAGE = 1;
  private static String[] PERMISSIONS_STORAGE = {
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.WRITE_EXTERNAL_STORAGE
  };

  /**
   * Creates a new instance of a <code>ClientDatabase</code>.
   * 
   * @param filepath
   *          the pathname of the file to read and store the data from
   */
  public ClientDatabase(String filepath) {
    this.filepath=filepath;
//    this.clients = new HashMap<String, Patient>();
//    serialize(clients);
    this.clients = this.deserialize();
  }

  /**
   * Deserializes the file storing the serialized <code>HashMap</code> for the
   * ClientDatabase.
   * 
   * @return the HashMap that stores all the clients
   */
  @SuppressWarnings("unchecked")
  public HashMap<String, Patient> deserialize() {
    HashMap<String, Patient> object = null;
    try {
      FileInputStream fileIn = new FileInputStream(filepath);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      object = (HashMap<String, Patient>) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException in) {
      in.printStackTrace();
    } catch (ClassNotFoundException cl) {
      System.out.println("Patient class not found");
      cl.printStackTrace();
    }
    return object;
  }

  /**
   * Serializes the HashMap to a file.
   * 
   * @param clients
   *          the HashMap to be serialized
   */
  public void serialize(HashMap<String, Patient> clients) {
    try {
      FileOutputStream fileOut = new FileOutputStream(this.filepath);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(clients);
      out.close();
      fileOut.close();
    } catch (IOException in) {
      in.printStackTrace();
    }
  }

  /**
   * Adds a <code>Patient</code> to the <code>ClientDatabase</code>.
   * 
   * @param patient
   *          the <code>Patient</code> instance to be added
   */
  public void addClient(Patient patient) {
    String key = patient.getEmail();
    this.clients.put(key, patient);
    serialize(clients);
  }

  /**
   * Removes a specific <code>Patient</code> to the <code>ClienttDatabase</code>
   * 
   * @param email
   *          the <code>email</code> of the <code>Patient</code> to be removed.
   */
  public void removeClient(String email) {
    this.clients.remove(email);
    serialize(clients);
  }

  /**
   * Returns HashMap of <code>Clients</code>.
   * 
   * @return the clients
   */
  public HashMap<String, Patient> getClient() {
    return clients;
  }

  /**
   * Translate the file info from a file into hashmaps.
   * 
   * @param filepath
   *          File to be read from
   * @throws IOException
   *           In case file cannot be found
   * @throws ParseException
   *           In case the file is invalid
   */
  public void addClientsFromFile(String filepath) throws IOException, ParseException {
    // Path filePath = Paths.get(filepath);
    // HashMap<String, Patient> clients = new HashMap<String, Patient>();
    String line;
    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + filepath);
    // reads client.txt line by line
    try{
    FileReader input = new FileReader(file);
    BufferedReader reader = new BufferedReader(input);

    while ((line = reader.readLine()) != null) {
      String lastName;
      String firstName;
      String email;
      String password;
      final String address;
      final String ccNumber;
      final Date expiryDate;
      String[] splitArray = line.split(";");

      lastName = splitArray[0];
      firstName = splitArray[1];
      email = splitArray[2];
      address = splitArray[3];
      ccNumber = splitArray[4];
      DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
      expiryDate = date.parse(splitArray[5]);
      password = splitArray[6];
      Patient addThisPatient = new Patient(email, firstName, lastName, address, password, ccNumber, expiryDate);
      this.clients.put(email, addThisPatient);

    }
    reader.close();
  }
  catch (FileNotFoundException ex) {
    Log.w("path", "FileNotFoundException");
  } catch (IOException ex2) {
    Log.w("path", "IOException");
  }
    serialize(this.clients);

    // testing
    //for (String key : this.clients.keySet()) {
    //  System.out.println("KEY:  " + key + "   VALUE:  " + this.clients.get(key));
    //}
  }

  /**
   * Returns a boolean to see if there is a client of that passowrd in the database
   *
   * @param password, the password to look for
   *                  It is a string representing a password of whatever length
   * @return a boolean representing if the password has been found or not
     */

  public boolean checkPassword(String password){
    for(Patient c: clients.values()){
      if(c.getPassword() == password){
        return true;
      }
    }
    return false;
  }

  public void setClientEmail(String newEmail, String oldEmail){
    Patient temp = clients.get(oldEmail);
    clients.remove(oldEmail);
    temp.setEmail(newEmail);
    clients.put(newEmail, temp);

  }

  @Override
  public String toString() {
    String result = "";
    for (String email: this.clients.keySet()) {
      result += email + "\n" + "\n" + this.clients.get(email)+ "\n" + "\n";
    }
    return result;
  }
}
