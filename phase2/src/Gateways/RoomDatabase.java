package Gateways;

import Gateways.Interfaces.IRoomDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * Class to get room Collection from the database and performing actions on the room database
 * @author Akshat Ayush
 */
public class RoomDatabase implements IRoomDatabase {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> roomCollection;

    /**
     * Constructor to initialize the mongo client, database and the room collection to be used by the room database
     * @param mongoClient: object of mongo client
     */
    public RoomDatabase(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("conference-database");
        this.roomCollection = database.getCollection("rooms");
    }

    /**
     * Method to return the rooms' data stored in the database
     * @return List of Map where each map represents the data associated with a room entity
     */
    @Override
    public List<Map<String, List<String>>> getRooms() {
        List<Map<String, List<String>>> roomList = new ArrayList<>();
        List<Document> roomDocuments = roomCollection.find().into(new ArrayList<>());
        for(Document roomDocument: roomDocuments) {
            Map<String, List<String>> room = new HashMap<>();
            List<String> roomInfo = new ArrayList<>();
            roomInfo.add(roomDocument.getString("roomId"));
            roomInfo.add(roomDocument.getInteger("capacity").toString());
            roomInfo.add(roomDocument.getBoolean("hasProjector").toString());
            roomInfo.add(roomDocument.getBoolean("hasAudioSystem").toString());
            roomInfo.add(roomDocument.getInteger("powerSockets").toString());
            room.put("roomInfo", roomInfo);
            room.put("occupiedTimes", roomDocument.getList("occupiedTimes", String.class));
            roomList.add(room);
        }
        return roomList;
    }

    /**
     * Method to save the rooms' data from the program into the database
     *
     * @param roomList: List of map where each map represents the data associated with a room entity
     */
    @Override
    public void saveRoomList(List<Map<String, List<String>>> roomList) {
        List<Document> roomDocumentList = new ArrayList<>();
        for(Map<String, List<String>> room: roomList) {
            Document roomDoc = new Document();
            List<String> roomInfo = room.get("roomInfo");
            roomDoc.append("roomId", roomInfo.get(0));
            roomDoc.append("capacity", roomInfo.get(1));
            roomDoc.append("hasProjector", roomInfo.get(2));
            roomDoc.append("hasAudioSystem", roomInfo.get(3));
            roomDoc.append("powerSockets", roomInfo.get(4));
            roomDoc.append("occupiedTimes", room.get("occupiedTimes"));

            roomDocumentList.add(roomDoc);
        }
        roomCollection.deleteMany(new Document());
        roomCollection.insertMany(roomDocumentList);
    }
}
