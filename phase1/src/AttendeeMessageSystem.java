public class AttendeeMessageSystem extends MessageSystem{

    public AttendeeMessageSystem(ConversationManager cManager, MessageManager mManager, AttendeeManager aManager,
                                 OrganizerManager oManager, SpeakerManager sManager, EventManager eManager) {
        super(cManager, mManager, aManager, oManager, sManager, eManager);
    }

    public void attendeeToSingle(String attendeeId, String recipientId, String content, String userType){
        Conversation convo = singleMessage(attendeeId, recipientId, content);

        switch(userType){
            case "attendee":
                attendeeManager.addConversation(recipientId, convo.getId());
            case "speaker":
                speakerManager.addConversation(recipientId, convo.getId());
        }
        attendeeManager.addConversation(attendeeId, convo.getId());
    }
}

