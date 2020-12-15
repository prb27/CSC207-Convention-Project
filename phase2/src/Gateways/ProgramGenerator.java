package Gateways;

import Controllers.MasterSystem;
import UseCases.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.*;

/**
 * This class stores the methods used by the Controllers.MasterSystem and Main.Main class to read the state of the program upon
 * running the program and save the state of the program upon closing the program.
 * The functionalities include:
 *  - reading the serialized Controllers.MasterSystem file.
 *  - writing the most updated state of the Controllers.MasterSystem class into the serialized Controllers.MasterSystem file.
 * @author Akshat Ayush
 */
public class ProgramGenerator implements Serializable{

    MongoClientURI uri;
    MongoClient mongoClient;

    AttendeeDatabase attendeeDatabase;
    OrganizerDatabase organizerDatabase;
    SpeakerDatabase speakerDatabase;
    MessageDatabase messageDatabase;
    ConversationDatabase conversationDatabase;
    EventDatabase eventDatabase;
    RoomDatabase roomDatabase;

    AttendeeManager attendeeManager;
    OrganizerManager organizerManager;
    SpeakerManager speakerManager;
    AdminManager adminManager;
    MessageManager messageManager;
    ConversationManager conversationManager;
    EventManager eventManager;
    RoomManager roomManager;

    public ProgramGenerator() {
        this.uri = new MongoClientURI("mongodb+srv://dbAdmin:dbAdminPassword@conference-cluster.ayrxj.mongodb.net/conference-database?retryWrites=true&w=majority");
        this.mongoClient = new MongoClient(uri);
        this.attendeeDatabase = new AttendeeDatabase(mongoClient);
        this.organizerDatabase = new OrganizerDatabase(mongoClient);
        this.speakerDatabase = new SpeakerDatabase(mongoClient);
        this.messageDatabase = new MessageDatabase(mongoClient);
        this.conversationDatabase = new ConversationDatabase(mongoClient);
        this.eventDatabase = new EventDatabase(mongoClient);
        this.roomDatabase = new RoomDatabase(mongoClient);

        this.attendeeManager = new AttendeeManager(attendeeDatabase);
        this.organizerManager = new OrganizerManager(organizerDatabase);
        this.speakerManager = new SpeakerManager(speakerDatabase);
        this.adminManager = new AdminManager();
        this.messageManager = new MessageManager(messageDatabase);
        this.conversationManager = new ConversationManager(conversationDatabase);
        this.eventManager = new EventManager(eventDatabase);
        this.roomManager = new RoomManager(roomDatabase);
    }

    /**
     * This method loads the data from the database into the respective use case classes
     * @author Juan Yi Loke, Akshat
     * @return An instance of the MasterSystem with the use case classes with the loaded data from the database
     * if the loading fromm database is successful or a new instance of a MasterSystem if an exception occurs
     */
    public MasterSystem readFromDatabase() {
        try {
            attendeeManager.loadFromDatabase();
            organizerManager.loadFromDatabase();
            speakerManager.loadFromDatabase();
            messageManager.loadFromDatabase();
            conversationManager.loadFromDatabase();
            eventManager.loadFromDatabase();
            roomManager.loadFromDatabase();
            adminManager.createAdmin();
            return new MasterSystem(attendeeManager, organizerManager, speakerManager, adminManager, messageManager,
                    conversationManager, eventManager, roomManager);
        } catch (Exception e) {
            e.printStackTrace();
            return new MasterSystem();
        }
    }

    /**
     * serialize and write the latest state of the Controllers.MasterSystem class into the serialized Controllers.MasterSystem file
     * @author Juan Yi Loke
     * @param filePath: the file name of the serialized Controllers.MasterSystem file
     * @param object: the Controllers.MasterSystem class that needs to be serialized into a .ser file
     */
    public void saveToFile(MasterSystem object, String filePath){
        try {
            File serFile = new File(filePath + ".ser");
            if(!serFile.exists())
                serFile.createNewFile();
            OutputStream file = new FileOutputStream(filePath + ".ser");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(object);
            output.close();

        } catch (IOException ex) {
            System.out.println("IOException while saving data");
            ex.printStackTrace();
        }
    }

}

