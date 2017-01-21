package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * A Class that represent a <code>AdminDatabase</code>.
 * 
 * @author Mandy
 *
 */
public class AdminDatabase extends Database {

  private String filepath;
  private HashMap<String, Doctor> admins;

  /**
   * Creates a new instance of a <code>AdminDatabase</code>.
   * 
   * @param filepath
   *          the pathname of the file to read and store the data from
   */
  public AdminDatabase(String filepath) {
    this.filepath = filepath;
    this.admins = deserialize();
  }

  /**
   * Deserializes the file storing the serialized <code>HashMap</code> for the
   * AdminDatabase.
   * 
   * @return the HashMap that stores all the admins
   */
  @SuppressWarnings("unchecked")
  public HashMap<String, Doctor> deserialize() {
    HashMap<String, Doctor> object = null;
    try {
      FileInputStream fileIn = new FileInputStream(this.filepath);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      object = (HashMap<String, Doctor>) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException in) {
      in.printStackTrace();
    } catch (ClassNotFoundException cl) {
      System.out.println("Doctor class not found");
      cl.printStackTrace();
    }
    return object;
  }

  /**
   * Serializes the HashMap to a file.
   * 
   * @param adminHash
   *          the HashMap to be serialized
   */
  public void serialize(HashMap<String, Doctor> adminHash) {
    try {
      FileOutputStream fileOut = new FileOutputStream(this.filepath);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(admins);
      out.close();
      fileOut.close();
    } catch (IOException in) {
      in.printStackTrace();
    }
  } // end serialize

  /**
   * Adds a <code>Doctor</code> to the <code>AdminDatabase</code>.
   * 
   * @param doctor
   *          the <code>doctor</code> instance to be added
   */
  public void addAdmin(Doctor doctor) {
    String key = doctor.getEmail();
    this.admins.put(key, doctor);
    serialize(admins);
  }

  /**
   * Removes a specific <code>Doctor</code> to the <code>AdminDatabase</code>.
   * 
   * @param adminNumber
   *          the <code>adminNumber</code> of the <code>Doctor</code> to be
   *          removed.
   */
  public void removeAdmin(String adminNumber) {
    this.admins.remove(adminNumber);
    serialize(admins);
  }

  /**
   * Returns HashMap of <code>admins</code>.
   * 
   * @return the admins
   */
  public HashMap<String, Doctor> getadmins() {
    return admins;
  }

  /**
   * Modifies the HashMap of <code>admins</code>.
   * 
   * @param admins
   *          the new set of admins
   */
  public void setadmins(HashMap<String, Doctor> admins) {
    this.admins = admins;
  }

} // end AdminDatabase
