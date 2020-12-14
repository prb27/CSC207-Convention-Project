package Gateways;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class AttendeeDatabase extends UserDatabase implements IAttendeeDatabase{

    public AttendeeDatabase(MongoClient mongoClient){
        super(mongoClient);
    }


    @Override
    public List<Map<String, List<String>>> getAttendees(){
        List<Map<String, List<String>>> attendeeList = new ArrayList<>();
        List<Document> attendeeDocuments = userCollection.find(eq("userType", "attendee")).into(new ArrayList<>());
        for(Document attendeeDocument: attendeeDocuments) {
            Map<String, List<String>> attendee = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = attendeeDocument.getString("username");
            credentials.add(username);
            credentials.add(attendeeDocument.getString("password"));
            attendee.put("credentials", credentials);
            attendee.put("contacts", getContactsForUser(username));
            attendee.put("conversations", getConversationsForUser(username));
            attendee.put("eventsAttending", getEventsAttendingForUser(username));
            attendeeList.add(attendee);
        }
        return attendeeList;
    }

    public List<String> getEventsAttendingForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        @SuppressWarnings("unchecked")
        List<String> eventsAttending = (ArrayList<String>) user.get(0).get("conversations");
        return eventsAttending;
    }

}
