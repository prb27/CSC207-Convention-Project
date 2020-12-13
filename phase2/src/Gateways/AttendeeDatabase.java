package Gateways;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendeeDatabase extends UserDatabase implements IAttendeeDatabase{

    public AttendeeDatabase(MongoClient mongoClient){
        super(mongoClient);
    }


    @Override
    public List<Map<String, String>> getAttendees(){
        // We start by getting the collection we want. In this case, the attendee. We have already instantiated it above.
        // Now, for our collection, we store documents of data related to the attendee entity. Thus, we must
        // store every document in a data structure. Let's use a list.

        //userType
        DBCursor cursor = userCollection.find();

        while(cursor.hasNext()){
        }


    }

    @Override
    public List<String> getEventsAttending(String attendee) {
        return null;
    }

}
