package Gateways;

import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class AdminDatabase extends UserDatabase implements IAdminDatabase{

    public AdminDatabase(MongoClient mongoClient) {
        super(mongoClient);
    }

    @Override
    public Map<String, List<String>> getAdmin() {
        Map<String, List<String>> admin = new HashMap<>();
        List<Document> adminDocument = userCollection.find(eq("userType", "admin")).into(new ArrayList<>());
        List<String> credentials = new ArrayList<>();
        String username = adminDocument.get(0).getString("username");
        credentials.add(username);
        credentials.add(adminDocument.get(0).getString("password"));
        admin.put("credentials", credentials);
        admin.put("contacts", getContactsForUser(username));
        admin.put("conversations", getConversationsForUser(username));
        return admin;
    }
}
