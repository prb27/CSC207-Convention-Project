import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MessageSystem {
    ConversationManager convoManager;
    MessageManager messageManager;
    AttendeeManager attendeeManager;
    OrganizerManager organizerManager;
    SpeakerManager speakerManager;
    EventManager eventManager;

    public MessageSystem(ConversationManager cManager, MessageManager mManager,
                         AttendeeManager aManager, OrganizerManager oManager, SpeakerManager sManager,
                         EventManager eManager){
        this.convoManager = cManager;
        this.messageManager = mManager;
        this.attendeeManager = aManager;
        this.organizerManager = oManager;
        this.speakerManager = sManager;
        this.eventManager = eManager;
    }

    //helper: sends a message with single recipient
    Conversation singleMessage(String senderId, String recipientId, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.add(recipientId);

        Conversation conversation = convoManager.createNewConversation(p);
        Message message = messageManager.sendMessageSingle(senderId, recipientId, content, conversation.getId());
        conversation.setConvoRoot(message.getId());

        return conversation;
    }

    //helper: sends a message with multiple recipients
    Conversation multiMessage(String senderId, ArrayList<String> recipientIds, String content){
        ArrayList<String> p = new ArrayList<>();
        p.add(senderId);
        p.addAll(recipientIds);

        Conversation conversation = convoManager.createNewConversation(p);
        Message message = messageManager.sendMessageMulti(senderId, recipientIds, content, conversation.getId());
        conversation.setConvoRoot(message.getId());

        return conversation;
    }

    boolean removeMessage(String messageId){
        return false;
    }
//    //handles phase 1 specification that says organizers can message all attendees
//    public void organizerToAllAttendees(String organizerId, String content){
//        ArrayList<String> recipientIds = new ArrayList<>();
//        for(Attendee a: attendeeManager.getAllAttendees()){
//            recipientIds.add(a.getUserId());
//        }
//
//        Conversation convo =  multiMessage(organizerId, recipientIds, content);
//        for(String id: recipientIds){
//            attendeeManager.addConversation(id, convo.getId());
//        }
//
//        organizerManager.addConversation(organizerId, convo.getId());
//    }
//
//    //handles phase 1 specification that says organizers can message a single attendee
//    public void organizerToSingleAttendee(String organizerId, String attendeeId, String content){
//
//        Conversation convo = singleMessage(organizerId, attendeeId, content);
//
//        attendeeManager.addConversation(attendeeId, convo.getId());
//
//        organizerManager.addConversation(organizerId, convo.getId());
//    }
//
//    //handles phase 1 specification that says organizers can message all speakers
//    public void organizerToAllSpeakers(String organizerId, String content){
//        ArrayList<String> recipientIds = new ArrayList<>();
//        for(Speaker s: speakerManager.getSpeakers()){
//            recipientIds.add(s.getUserId());
//        }
//
//        Conversation convo = multiMessage(organizerId, recipientIds, content);
//        for(String id: recipientIds){
//            speakerManager.addConversation(id, convo.getId());
//        }
//
//        organizerManager.addConversation(organizerId, convo.getId());
//    }
//
//    //handles phase 1 specification that says organizers can message a single attendee
//    public void organizerToSingleSpeaker(String organizerId, String speakerId, String content){
//        Conversation convo = singleMessage(organizerId, speakerId, content);
//
//        speakerManager.addConversation(speakerId, convo.getId());
//
//        organizerManager.addConversation(organizerId, convo.getId());
//    }

    public boolean speakerByTalk(String speakerId, String eventName, String content){
        Event talk = eventManager.getEvent(eventName);

        if(talk != null){
            ArrayList<String> recipientIds = new ArrayList<>(talk.getAttendeeList());
            Conversation convo = multiMessage(speakerId, recipientIds, content);
            for(String id: recipientIds){
                if(organizerManager.getOrganizer(id) != null){
                    organizerManager.addConversation(id, convo.getId());
                } else if(attendeeManager.getAttendee(id) != null){
                    attendeeManager.addConversation(id, convo.getId());
                }
            }
        }
        return false;
    }

    public void speakerByAllTalks(String speakerId, ArrayList<String> eventNames, String content){
        ArrayList<String> recipientIds = new ArrayList<>();

        for(Event e: eventManager.getEventList()){
            if(eventNames.contains(e.getEventName())){
                recipientIds.addAll(e.getAttendeeList());
            }
        }
        //do the below to remove duplicate attendees so they dont get messaged multiple times
        Set<String> set = new HashSet<>(recipientIds);
        recipientIds.clear();
        recipientIds.addAll(set);

        if(!recipientIds.isEmpty()){
            Conversation convo = multiMessage(speakerId, recipientIds, content);
            for(String id: recipientIds){
                if(organizerManager.getOrganizer(id) != null){
                    organizerManager.addConversation(id, convo.getId());
                } else if(attendeeManager.getAttendee(id) != null){
                    attendeeManager.addConversation(id, convo.getId());
                }
            }
        }
    }
    
}
