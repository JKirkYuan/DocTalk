package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Database {

  /**
   * Given a file name corresponding to a file with each line representing data
   * for an object in the database and the parameters and separated by ; returns
   * an <code>ArrayList</code> with that given data.
   * 
   * modified reading files code from: https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
   *
   * @param filename
   *          The file to be read
   * @return <code>ArrayList</code> with data from the file
   */
  public ArrayList<String> readFile(String filename) {
    try {
      ArrayList<String> fileContent = new ArrayList<>();
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        fileContent.add(line);
      }
      bufferedReader.close();
      fileReader.close();
      return fileContent;

    } catch (Exception ex) {
      return new ArrayList<String>();
    }
  }
}
