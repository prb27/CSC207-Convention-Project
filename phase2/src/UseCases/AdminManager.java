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
    private Admin admin = new Admin("admin", "admin");
//    private List<Admin> admins = new ArrayList<>();
//
//    public boolean createAdmin(String username, String password) {
//        for (Admin admin: admins){
//            if (admin.getUsername().equals(username)){
//                return false;
//            }
//        }
//        admins.add(new Admin(username, password));
//        return true;
//    }

    /**
     * this method checks if the username and password are correct
     * @param username: the inputted username (param_type: String)
     * @param password: the inputted password to be checked (param_type: String)
     * @return boolean whether username and password are correct
     */
    public boolean checkPassword(String username, String password) {
        return username.equals(admin.getUserId()) && password.equals(admin.getPassword());
    }


//    private Admin getAdmin(String username){
//        for(Admin admin: admins){
//            if(admin.getUsername().equals(username)){
//                return admin;
//            }
//        }
//        return null;
//    }

    public boolean isAdmin(String username){
        return username.equals(admin.getUserId());
    }

    public List<String> getAllAdminNames(){
        // List of only 1 admin!
        List<String> names = new ArrayList<>();
        names.add(admin.getUserId());
        return names;
    }

}