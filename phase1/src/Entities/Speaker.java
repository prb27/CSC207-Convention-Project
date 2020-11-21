package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores all the information relating to a Entities.Speaker
 * and provides a single getter and setter to extract and replace
 * the information
 * Entities.Speaker is a subclass of Entities.User
 * @author Vladimir Caterov
 * @see User
 */
public class Speaker extends User implements Serializable {
    private ArrayList<HashMap<String, String>> listOfTalks;

    /**
     * a constructor that creates a Entities.Speaker object
     *
     * @param username: the username of this Entities.Speaker
     * @param password: the password of this Entities.Speaker
     * @return a Entities.Speaker object
     */
    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new ArrayList<>();
    }

    /**
     * returns the list of all talks that this Entities.Speaker is speaking at
     * @return HashMap<String, String> listOfTalks
     */
    public ArrayList<HashMap<String, String>> getListOfTalks() {
        return listOfTalks;
    }
    /**
     * updates the list of all events that this Entities.Speaker is speaking at
     * @param listOfTalks: the new list of all talks(param_type: HashMap<String, String>)
     * @return void
     */
    public void setListOfTalks(ArrayList<HashMap<String, String>> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}