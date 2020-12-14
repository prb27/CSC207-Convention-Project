package UseCases;

import Entities.Admin;
import Entities.Organizer;
import Gateways.Interfaces.IAdminDatabase;

import java.io.Serializable;
import java.util.List;

/**
 * this class manages (stores and updates) the Admin
 * It also supports the following Admin functionalities
 *         - create the Admin account
 *         - check password for the Admin
 * @author Khoa Pham
 * @see Entities.Admin
 */
public class AdminManager implements Serializable {
    private Admin admin;

    public void createAdmin() {
        admin = new Admin();
    }

    /**
     * this method checks if the username and password are correct
     * @param username: the inputted username (param_type: String)
     * @param password: the inputted password to be checked (param_type: String)
     * @return boolean whether username and password are correct
     */
    public boolean checkPassword(String username, String password){
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }

    public String getAdminName(){
        return admin.getUsername();
    }

    public boolean isAdmin(String username){
        return getAdminName().equals(username);
    }



    IAdminDatabase adminDatabase;
    public AdminManager(IAdminDatabase adminDatabase){
        this.adminDatabase = adminDatabase;
    }

    public void loadFromDatabase() {

        List<String> listOfContacts = admin.get("listOfContacts");
        List<String> listOfConversations = admin.get("listOfConversations");
        String username = admin.get("credentials").get(0);
        String password = admin.get("credentials").get(1);
        Organizer newOrganizer =  new Admin(username, password);
        newOrganizer.setContacts(listOfContacts);
        newOrganizer.setConversations(listOfConversations);

    }



}
