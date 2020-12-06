package Gateways;

import java.util.List;
import java.util.Map;

/**
 * Interface for the AttendeeDatabase class to get Attendee data from the SQL database
 * @author Akshat Ayush
 */
public interface IAttendeeDatabase {

    public Map<String, String> getAllAttendeesFromDb();
    public List<String> getContactsFromDb(String username);
}
