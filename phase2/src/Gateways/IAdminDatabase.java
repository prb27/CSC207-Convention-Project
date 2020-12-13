package Gateways;

import org.bson.Document;

import java.util.List;

public interface IAdminDatabase {

    public List<Document> getAttendeeList();

}