package Gateways;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;

import java.util.List;

public class Database implements IAttendeeDatabase {

    //dbname = conference-database
    //password = dbAdminPassword
    MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://dbAdmin:dbAdminPassword@conference-cluster.ayrxj.mongodb.net/conference-database?retryWrites=true&w=majority");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("conference-database");


    @Override
    public List<Document> getAttendeeList() {
        return null;
    }
}
