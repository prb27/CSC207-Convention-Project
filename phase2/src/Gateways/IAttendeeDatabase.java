package Gateways;

import org.bson.Document;
import java.util.List;

public interface IAttendeeDatabase {

    public List<Document> getAttendeeList();
}