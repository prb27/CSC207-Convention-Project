import java.util.ArrayList;

public class Speaker extends User{
    private ArrayList<String> listOfTalks;
    public Speaker(String username, String password){
        super(username, password);
    }

    public ArrayList<String> getListOfTalks() {
        return listOfTalks;
    }
}
