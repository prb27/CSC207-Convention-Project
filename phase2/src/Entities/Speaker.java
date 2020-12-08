package Entities;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class stores all the information relating to a Speaker
 * and provides a single getter and setter to extract and replace
 * the information
 * Speaker is a subclass of User
 * @author Vladimir Caterov
 * @see User
 */
public class Speaker extends User implements Serializable {
    private HashMap<String, String> listOfTalks;

    /**
     * A constructor that creates a Speaker object
     *
     * @param username: the username of this Speaker
     * @param password: the password of this Speaker
     */
    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new HashMap<>();
    }

    /**
     * Returns the list of all talks that this Speaker is speaking at
     * @return List<HashMap<String, String>>: Returns an List of HashMaps containing a reference to an
     * event time as the key and event name as the value
     */
    public HashMap<String, String> getListOfTalks() {
        return listOfTalks;
    }
    /**
     * Updates the list of all events that this Speaker is speaking at
     * @param listOfTalks: The new list of all talks(param_type: HashMap<String, String>)
     */
    public void setListOfTalks(HashMap<String, String> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}