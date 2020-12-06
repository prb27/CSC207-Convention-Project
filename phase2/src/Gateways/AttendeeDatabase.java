package Gateways;

import API.AzureSQL_API;

import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;

public class AttendeeDatabase implements IAttendeeDatabase{

    private final AzureSQL_API api;

    public AttendeeDatabase(AzureSQL_API api) {
        this.api = api;
    }


    @Override
    public Map<String, String> getAllAttendeesFromDb() {
        try {
            Map<String, String> attendeeList = new HashMap<>();
            ResultSet users = api.getAllColumnsFromTable("UserList", "AccountType=Attendee");
            while(users.next()) {
                attendeeList.put(users.getString("Username"), users.getString("Password"));
            }
            return attendeeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getContactsFromDb(String username) {
        try {
            List<String> contactList = new ArrayList<>();
            ResultSet contacts = api.getSelectedColumnsFromTable(Arrays.asList("Contact"), "ContactList", "Username="+username);
            while(contacts.next()) {
                contactList.add(contacts.getString(0));
            }
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
