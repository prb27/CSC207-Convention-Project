package UseCases;

import Entities.Admin;

import java.util.ArrayList;
import java.util.List;


/**
 * this class manages (stores and updates) the Admin
 * It also supports the following Admin functionalities
 *         - create the Admin account
 *         - check password for the Admin
 * @author Khoa Pham
 * @see Entities.Admin
 */
public class AdminManager {
    private List<Admin> admins = new ArrayList<>();
    private Admin admin;

    public void createAdmin() {
        if (admins.size() < 1)
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



}
