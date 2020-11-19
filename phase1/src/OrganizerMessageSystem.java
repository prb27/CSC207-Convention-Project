import java.util.ArrayList;

public class OrganizerMessageSystem extends MessageSystem {
    public OrganizerMessageSystem(ConversationManager cManager, MessageManager mManager, AttendeeManager aManager,
                                  OrganizerManager oManager, SpeakerManager sManager, EventManager eManager) {
        super(cManager, mManager, aManager, oManager, sManager, eManager);
    }

    //handles phase 1 specification that says organizers can message all attendees
    //recipientIds should be a list of all the User type entities in the system
    public void organizerToAll(String organizerId, String content, String userType, ArrayList<String> recipientIds){

        Conversation convo = multiMessage(organizerId, recipientIds, content);
        switch(userType){
            case "attendee":
                for(String id: recipientIds){
                    attendeeManager.addConversation(id, convo.getId());
                }
            case "organizer":
                for(String id: recipientIds){
                    organizerManager.addConversation(id, convo.getId());
                }
            case "speaker":
                for(String id: recipientIds){
                    speakerManager.addConversation(id, convo.getId());
                }
        }
        organizerManager.addConversation(organizerId, convo.getId());
    }

    //handles phase 1 specification that says organizers can message a single attendee
    public void organizerToSingle(String organizerId, String recipientId, String content, String userType){

        Conversation convo = singleMessage(organizerId, recipientId, content);

        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convo.getId());
            case "organizer":
                organizerManager.addConversation(recipientId, convo.getId());
            case "speaker":
                speakerManager.addConversation(recipientId, convo.getId());
        }
        organizerManager.addConversation(organizerId, convo.getId());
    }
}
