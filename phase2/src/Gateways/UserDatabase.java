package Gateways;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public abstract class UserDatabase {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected MongoCollection<Document> userCollection;

    public UserDatabase(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("conference-database");
        this.userCollection = database.getCollection("users");
    }

    //methods to return list of contacts and list of conversations
    public List<String> getContactsForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("contacts", String.class);
    }

    public List<String> getConversationsForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("conversations", String.class);
    }

    public List<String> getEventsAttendingForUser(String username) {
        List<Document> user = userCollection.find(eq("username", username)).into(new ArrayList<>());
        return user.get(0).getList("conversations", String.class);
    }

}
