package Gateways.Interfaces;


import java.util.*;

public interface IMessageDatabase {

    List<Map<String, List<String>>> getMessageList();
    void saveMessageList(List<Map<String, List<String>>> messageList);

}