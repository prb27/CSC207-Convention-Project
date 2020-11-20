
import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<HashMap<String, String>> listOfTalks;

    /**
     * a constructor that creates a Speaker object
     *
     * @param username: the username of this Speaker
     * @param password: the password of this Speaker
     * @return a Speaker object
     */
    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new ArrayList<>();
    }

    /**
     * returns the list of all talks that this Speaker is speaking at
     * @return HashMap<String, String> listOfTalks
     */
    public ArrayList<HashMap<String, String>> getListOfTalks() {
        return listOfTalks;
    }
    /**
     * updates the list of all events that this Speaker is speaking at
     * @param listOfTalks: the new list of all talks(param_type: HashMap<String, String>)
     * @return void
     */
    public void setListOfTalks(ArrayList<HashMap<String, String>> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}