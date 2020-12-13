package Scrap;
import java.io.*;

/**
 * This class stores the methods used by the Controllers.MasterSystem and Main class to read the state of the program upon
 * running the program and save the state of the program upon closing the program.
 * The functionalities include:
 *  - reading the serialized Controllers.MasterSystem file.
 *  - writing the most updated state of the Controllers.MasterSystem class into the serialized Controllers.MasterSystem file.
 * @author Vladimir Caterov
 */

public class CurrUsernameInfoFileHandler implements Serializable{

    public CurrUsernameInfoFileHandler(){
    }

    public void setName(String newName){
        saveToFile(newName, "currLoginInfo");
    }
    public String getName(){
        return readFromFile("currLoginInfo");
    }
    /**
     * read the serialized Controllers.MasterSystem file and return the deserialized Controllers.MasterSystem object
     * @author Vladimir Caterov
     * @param filePath: the name for the serialized Controllers.MasterSystem file
     * @return Controllers.MasterSystem: the deserialized Controllers.MasterSystem object
     */
    public String readFromFile(String filePath) {
        try {
            InputStream file = new FileInputStream(filePath + ".ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            String username = input.readObject().toString();
            input.close();

            return username;

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Cannot read from file, creating a new Controllers.MasterSystem");
            return null;
        }
    }

    /**
     * serialize and write the latest state of the Controllers.MasterSystem class into the serialized Controllers.MasterSystem file
     * @author Vladimir Caterov
     * @param filePath: the file name of the serialized Controllers.MasterSystem file
     * @param object: the Controllers.MasterSystem class that needs to be serialized into a .ser file
     */
    public void saveToFile(String object, String filePath){
        try {
            File serFile = new File(filePath + ".ser");
            if(!serFile.exists())
                serFile.createNewFile();
            OutputStream file = new FileOutputStream(filePath + ".ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(object);
            output.close();

        } catch (IOException ex) {
            System.out.println("IOException while saving data");
            ex.printStackTrace();
        }
    }

}