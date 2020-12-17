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

    public void createAdmin(String username, String password) {
        admins.add(new Admin(username, password));
    }

    /**
     * this method checks if the username and password are correct
     * @param username: the inputted username (param_type: String)
     * @param password: the inputted password to be checked (param_type: String)
     * @return boolean whether username and password are correct
     */
    public boolean checkPassword(String username, String password){
        Admin admin = getAdmin(username);
        if(admin!=null) {
            return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
        }
        return false;
    }


    private Admin getAdmin(String username){
        for(Admin admin: admins){
            if(admin.getUsername().equals(username)){
                return admin;
            }
        }
        return null;
    }

    public boolean isAdmin(String username){
        return getAdmin(username)!=null;
    }

    public List<String> getAllAdminNames(){

        List<String> names = new ArrayList<>();
        for(Admin admin: admins){
            names.add(admin.getUsername());
        }
        return names;
    }

}