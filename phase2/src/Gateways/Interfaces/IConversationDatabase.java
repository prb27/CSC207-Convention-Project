package Gateways.Interfaces;

import org.bson.Document;

import java.util.*;

public interface IConversationDatabase {

    List<Map<String, List<String>>> getConversationList();
    void saveConversationList(List<Map<String, List<String>>> conversationList);

}