import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SpeakerMessageSystem extends MessageSystem {
    public SpeakerMessageSystem(ConversationManager cManager, MessageManager mManager, AttendeeManager aManager,
                                OrganizerManager oManager, SpeakerManager sManager, EventManager eManager) {
        super(cManager, mManager, aManager, oManager, sManager, eManager);
    }

    public void speakerByTalk(String speakerId, String eventName, String content){
        Event talk = eventManager.getEvent(eventName);

        ArrayList<String> recipientIds = new ArrayList<>(talk.getAttendeeList());
        Conversation convo = multiMessage(speakerId, recipientIds, content);
        for(String id: recipientIds){
            if(organizerManager.getOrganizer(id) != null){ //should change to - organizerManager.isOrganizer(id) (VLAD)
                organizerManager.addConversation(id, convo.getId());
            } else if(attendeeManager.getAttendee(id) != null){
                attendeeManager.addConversation(id, convo.getId());
            }
        }
    }

    public void speakerByMultiTalks(String speakerId, ArrayList<String> eventNames, String content){
        ArrayList<String> recipientIds = new ArrayList<>();

        for(Event e: eventManager.getEventList()){
            if(eventNames.contains(e.getEventName())){
                recipientIds.addAll(e.getAttendeeList());
            }
        }
        //do the below 3 lines to remove duplicate attendees so they dont get messaged multiple times
        Set<String> set = new HashSet<>(recipientIds);
        recipientIds.clear();
        recipientIds.addAll(set);

        Conversation convo = multiMessage(speakerId, recipientIds, content);

        if(!recipientIds.isEmpty()){
            for(String id: recipientIds){
                if(organizerManager.getOrganizer(id) != null){ //should change to - organizerManager.isOrganizer(id) (VLAD)
                    organizerManager.addConversation(id, convo.getId());
                } else if(attendeeManager.getAttendee(id) != null){
                    attendeeManager.addConversation(id, convo.getId());
                }
            }
        }
    }
}
