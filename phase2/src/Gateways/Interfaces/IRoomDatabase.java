package Gateways.Interfaces;

import org.bson.Document;

import java.util.*;

public interface IRoomDatabase {

    List<Map<String, List<String>>> getRooms();
    void saveRoomList(List<Map<String, List<String>>> roomList);
}