package Gateways;

import org.bson.Document;
import java.util.List;

public interface InterfaceAttendeeDatabase {
// Whatever below is copied from AttendeeManager functionality. I pasted it here so it is easy for me to know what
// I need to do. For now, our database-usecase methods are focused solely on the storing and setting of any data stored
// by the use cases which are indavertenly stored by the entities.
// *      - creating new Entities.Attendee object
// *      - allow an Entities.Attendee to connects another Entities.User
// *      - add a new conversation to an Entities.Attendee's list of participating conversations
//            *      - get the list of contacts from a given Entities.Attendee
// *      - get an Entities.Attendee given their username
// *      - get all Attendees (done through getAttendeeList())
// *      - check the password of a given Entities.Attendee
    public List<Document> getAttendeeList();



}