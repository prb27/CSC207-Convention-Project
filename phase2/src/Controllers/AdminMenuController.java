package Controllers;

import Presenters.Interfaces.IAdminMenu;
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
    private IAdminMenu adminMenu;

    private List<String> allMessages;

    public AdminMenuController(AttendeeManager attendeeManager, SpeakerManager speakerManager,
                               OrganizerManager organizerManager, ConversationManager conversationManager,
                               ConversationMenuController conversationMenuController){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.conversationManager = conversationManager;
        this.conversationMenuController = conversationMenuController;
        this.allMessages = new ArrayList<>();

    }
    public List<String> getAllMessages() {
        List<String> messageList = new ArrayList<>();
        List<String> speakers = speakerManager.getAllSpeakerIds();
        List<String> organizers = organizerManager.getAllOrganizerIds();

        List<String> speakerConversationMessages = new ArrayList<>();
        List<String> organizerConversationMessages = new ArrayList<>();

        for (String speaker : speakers) {
            List<String> conversationIDS = speakerManager.getConversations(speaker);
            for (String conversationID : conversationIDS) {
                List<String> participants = conversationManager.getConvoParticipants(conversationID);
                for (String member : participants) {
                    if (!attendeeManager.isAttendee(member)) {
                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
                        speakerConversationMessages.addAll(orderedMessages);
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
                        organizerConversationMessages.addAll(orderedMessages);
                    }
                }

            }
        }

        for (String speakerMessage: speakerConversationMessages){
            for (String organizerMessage: organizerConversationMessages){
                if (!speakerMessage.equals(organizerMessage) && !allMessages.contains(speakerMessage) &&
                        !allMessages.contains(organizerMessage)){
                    allMessages.add(speakerMessage);
                    allMessages.add(organizerMessage);
                }
                else{
                    allMessages.add(speakerMessage);
                }
            }
        }
        return allMessages;
    }
    private void setAllMessages(List<String> newMessageList){
        this.allMessages = newMessageList;
    }

    public void updateMessages(String message){

        List<String> newMessages = new ArrayList<>();
        List<String> speakers = speakerManager.getAllSpeakerIds();
        List<String> organizers = organizerManager.getAllOrganizerIds();

        for (String speaker : speakers) {
            List<String> conversationIDS = speakerManager.getConversations(speaker);
            for (String conversationID : conversationIDS) {
                List<String> participants = conversationManager.getConvoParticipants(conversationID);
                for (String member : participants) {
                    if (!attendeeManager.isAttendee(member)) {
                        List<String> orderedMessages = conversationMenuController.orderedMessagesInConvo(conversationID);
                    }
                }

            }
        }


        for(String message1: allMessages){
        }
        setAllMessages(newMessages);
        adminMenu.loadMessages();
    }

}
