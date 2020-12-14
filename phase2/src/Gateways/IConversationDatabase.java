package Gateways;

import org.bson.Document;

import java.util.*;

public interface IConversationDatabase {

    public List<Map<String, List<String>>> getConversationList();


}