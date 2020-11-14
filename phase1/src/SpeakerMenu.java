import Messaging.MessageSystem;

import java.util.ArrayList;

public class SpeakerMenu {
    private SpeakerManager speakerManager;
    public MessageSystem messageSystem;

    public SpeakerMenu(SpeakerManager speakerManager){
        this.speakerManager = speakerManager;
    }

    public void addContact(String speakerUsername, String otherUsername){
        speakerManager.addContact(speakerUsername, otherUsername);
    }
    public void messageAllContacts(String username, String content){
        messageSystem.multiMessage(username, speakerManager.getContactsForSpeaker(username),content);

    }
    public void messageSingleContact(String username, String receiverUsername, String content){
        messageSystem.singleMessage(username, receiverUsername, content);
    }



}
