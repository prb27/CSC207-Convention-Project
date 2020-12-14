package Gateways;

import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class OrganizerDatabase extends UserDatabase implements IOrganizerDatabase{

    public OrganizerDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    @Override
    public List<Map<String, List<String>>> getOrganizers() {
        List<Map<String, List<String>>> organizerList = new ArrayList<>();
        List<Document> organizerDocuments = userCollection.find(eq("userType", "organizer")).into(new ArrayList<>());
        for(Document organizerDocument: organizerDocuments) {
            Map<String, List<String>> organizer = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = organizerDocument.getString("username");
            credentials.add(username);
            credentials.add(organizerDocument.getString("password"));
            organizer.put("credentials", credentials);
            organizer.put("contacts", getContactsForUser(username));
            organizer.put("conversations", getConversationsForUser(username));
            organizer.put("eventsAttending", getEventsAttendingForUser(username));
            organizerList.add(organizer);
        }
        return organizerList;
    }
}
