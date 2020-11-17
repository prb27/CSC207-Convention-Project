
import java.util.HashMap;

public class Speaker extends User{
    private HashMap<String, String> listOfTalks;

    public Speaker(String username, String password){
        super(username, password);
        this.listOfTalks = new HashMap<>();
    }

    public HashMap<String, String> getListOfTalks() {
        return listOfTalks;
    }
    public void setListOfTalks(HashMap<String, String> listOfTalks){
        this.listOfTalks = listOfTalks;

    }
}