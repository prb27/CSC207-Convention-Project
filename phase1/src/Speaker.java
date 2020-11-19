
import java.util.HashMap;

/**
 * This class stores all the information relating to a Speaker
 * and provides a single getter and setter to extract and replace
 * the information
 * Speaker is a subclass of User
 * @author Vladimir Caterov
 * @see User
 */
public class Speaker extends User{
    private HashMap<String, String> listOfTalks;

    /**
     * a constructor to create a Speaker object
     *
     * @param username: the username of this Speaker
     * @param password: the password of this Speaker
     * @return a Speaker object
     */
    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new HashMap<>();
    }

    /**
     * return the list of all events that this Speaker is talking at
     * @return HashMap<String, String> listOfTalks
     */
    public HashMap<String, String> getListOfTalks() {
        return listOfTalks;
    }
    /**
     * update the list of all events that this Speaker is speaking at
     * @param listOfTalks: the new list of all talks(param_type: HashMap<String, String>)
     * @return void
     */
    public void setListOfTalks(HashMap<String, String> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}