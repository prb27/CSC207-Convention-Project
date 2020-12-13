package Gateways;

import org.bson.Document;

import java.util.List;

public interface IEventDatabase {

    public List<Document> getAttendeeList();

}