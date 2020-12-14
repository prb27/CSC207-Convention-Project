package Gateways;

import Gateways.Interfaces.ISpeakerDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class SpeakerDatabase extends UserDatabase implements ISpeakerDatabase {



    public SpeakerDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    @Override
    public List<Map<String, List<String>>> getSpeakers() {
        List<Map<String, List<String>>> speakerList = new ArrayList<>();
        List<Document> speakerDocuments = userCollection.find(eq("userType", "speaker")).into(new ArrayList<>());
        for(Document speakerDocument: speakerDocuments) {
            Map<String, List<String>> speaker = new HashMap<>();
            List<String> credentials = new ArrayList<>();
            String username = speakerDocument.getString("username");
            credentials.add(username);
            credentials.add(speakerDocument.getString("password"));
            speaker.put("credentials", credentials);
            speaker.put("contacts", getContactsForUser(username));
            speaker.put("conversations", getConversationsForUser(username));
            speaker.put("eventsAttending", getEventsAttendingForUser(username));
            speaker.put("eventTimes", getEventTimesForSpeaker(username));
            speakerList.add(speaker);
        }
        return speakerList;
    }

    private List<String> getEventTimesForSpeaker(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("eventTimes", String.class);
    }
}
