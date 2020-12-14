package Gateways.Interfaces;

import org.bson.Document;

import java.util.*;

public interface IEventDatabase {

    public List<Map<String, List<String>>> getEventList();

}