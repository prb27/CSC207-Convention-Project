import java.util.ArrayList;
import java.util.HashMap;

public class Speaker extends User{
    private HashMap<String, String> listOfTalks;
    public Speaker(String username, String password){
        super(username, password);
    }

    public HashMap<String, String> getListOfTalks() {
        return listOfTalks;
    }
}