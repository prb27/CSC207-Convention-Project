package Gateways;

import org.bson.Document;

import java.util.*;

public interface IRoomDatabase {

    public List<Map<String, List<String>>> getRoomList();

}