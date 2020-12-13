package Gateways;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Database implements InterfaceAttendeeDatabase {

    //dbname = conference-database
    //password = dbAdminPassword
    MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://dbAdmin:dbAdminPassword@conference-cluster.ayrxj.mongodb.net/conference-database?retryWrites=true&w=majority");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase db = mongoClient.getDatabase("conference-database");
    DBCollection attendeeCollection = (DBCollection) db.getCollection("Attendee");


    // Not sure where to place this. Might move it somewhere else in the future. This is from a Piazza post. Not sure what
    // it does, will investigate.
//    public Database(){
////        Logger.getLogger("org.mongodb.drive").setLevel(Level.SEVERE);
////        String MongoDBAtlas = "mongodb+srv://dbAdmin:dbAdminPassword@conference-cluster.ayrxj.mongodb.net/conference-database?retryWrites=true&w=majority");
//
//        try (MongoClient mongoclient = MongoClients.create(MongoClientURI)) {
//            database = mongoClient.getDatabase("csc207");
//            MongoCollection<Document> usersCollection = database.getCollection("users");
//
//
//            try {
//                database.createCollection("data");
//            } catch (MongoCommandException e) {
//                database.getCollection("data").drop();
//            }
//        }
//    }

    @Override
    public List<Document> getAttendeeList(){
        // We start by getting the collection we want. In this case, the attendee. We have already instantiated it above.
        // Now, for our collection, we store documents of data related to the attendee entity. Thus, we must
        // store every document in a data structure. Let's use a list.

        List<Document> ListofAttendees = new ArrayList<>();
        DBCursor cursor = attendeeCollection.find();

        while(cursor.hasNext()){
            ListofAttendees.add((Document) cursor.next());
        }
        return ListofAttendees;
    }
}
