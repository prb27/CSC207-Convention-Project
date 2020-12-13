package Gateways;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class UserDatabase {

    protected MongoClient mongoClient;
    protected MongoDatabase database;
    protected DBCollection userCollection;

    public UserDatabase(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("conference-database");
        this.userCollection = (DBCollection) database.getCollection("Users");
    }

    //methods to return list of contacts and list of conversations

}
