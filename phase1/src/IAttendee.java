import java.util.ArrayList;

public interface IAttendee extends Contact {
    ArrayList<Integer> getReceivedMessagesIds();
    ArrayList<Integer> getSentMessagesIds();
    String getPassword();
    ArrayList<String> getContacts();
    void setContacts(ArrayList<String> contacts);
}
