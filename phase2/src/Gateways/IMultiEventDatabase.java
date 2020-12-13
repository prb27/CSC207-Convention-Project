package Gateways;

import org.bson.Document;

import java.util.List;

public interface IMultiEventDatabase {

    public List<Document> getAttendeeList();

}