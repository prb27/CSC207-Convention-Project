package Controllers;

import UseCases.AttendeeManager;
import UseCases.ConversationManager;
import UseCases.OrganizerManager;
import UseCases.SpeakerManager;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuController {

    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final ConversationManager conversationManager;
    private final ConversationMenuController conversationMenuController;

    public AdminMenuController(AttendeeManager attendeeManager, SpeakerManager speakerManager,
                               OrganizerManager organizerManager, ConversationManager conversationManager,
                               ConversationMenuController conversationMenuController){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;

    }
    public List<String> getAllMessages() {
        List<String> messageList = new ArrayList<>();
        List<String> speakers = speakerManager.getAllSpeakerIds();
        List<String> organizers = organizerManager.getAllOrganizerIds();

        for (String speaker : speakers) {
            List<String> conversationIDS = speakerManager.getConversations(speaker);
            for (String conversationID : conversationIDS) {
                List<String> participants = conversationManager.getConvoParticipants(conversationID);
                for (String member : participants) {
                    if (!attendeeManager.isAttendee(member)) {
                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
                        messageList.addAll(orderedMessages);
                    }
                }

            }
        }

        for (String organizer : organizers) {
            List<String> conversationIDS = organizerManager.getConversations(organizer);
            for (String conversationID : conversationIDS) {
                List<String> participants = conversationManager.getConvoParticipants(conversationID);
                for (String member : participants) {
                    if (!attendeeManager.isAttendee(member)) {
                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
                        messageList.addAll(orderedMessages);
                    }
                }

            }
        }
        return messageList;

    }

}
