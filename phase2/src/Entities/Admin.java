package Entities;

import java.io.Serializable;

/**
 * this class stores all info relating to an Admin
 * and provides getters to extract those info
 * Admin is a subclass of User
 * @author Khoa Pham
 * @see User
 * A few notes to consider:
 *    * disallows changes in username, password for now
 */
public class Admin extends User implements Serializable {

    /**
     * a constructor to create an Admin object
     * There will only be one Admin
     * this will be created automatically without interference of clients
     */
    public Admin() {
        super("Khoa", "Pham");
    }

    /**
     * returns the username of this Admin
     */
    public String getUsername() {
        return this.getUserId();
    }
}
