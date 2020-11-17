import java.util.ArrayList;

public class OrganizerMessageSystem extends MessageSystem {
    public OrganizerMessageSystem(ConversationManager cManager, MessageManager mManager, AttendeeManager aManager,
                                  OrganizerManager oManager, SpeakerManager sManager, EventManager eManager) {
        super(cManager, mManager, aManager, oManager, sManager, eManager);
    }

    //handles phase 1 specification that says organizers can message all attendees
    public void organizerToAll(String organizerId, String content, String userType){
        ArrayList<String> recipientIds = new ArrayList<>();
        switch(userType){
            case "attendee":
                for(Attendee a: attendeeManager.getAllAttendees()){
                    recipientIds.add(a.getUserId());
                }
            case "organizer":
                for(Organizer o: organizerManager.getAllOrganizers()){
                    recipientIds.add(o.getUserId());
                }
            case "speaker":
                for(Speaker s: speakerManager.getSpeakers()){
                    recipientIds.add(s.getUserId());
                }
        }

        ArrayList<String> participants = new ArrayList<>();
        participants.add(organizerId);
        participants.addAll(recipientIds);

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
        ArrayList<String> participants = new ArrayList<>();
        participants.add(organizerId);
        participants.add(recipientId);

        Conversation convo = convoManager.createNewConversation(participants);
        Message message = messageManager.sendMessageSingle(organizerId, recipientId, content, convo.getId());
        convo.setConvoRoot(message.getId());

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
