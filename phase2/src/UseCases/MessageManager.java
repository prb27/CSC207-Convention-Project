package UseCases;

import Entities.Message;
import Gateways.Interfaces.IMessageDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class stores and updates all the messages in the system, as well as send information about those messages to appropriate classes
 * This includes the following responsibilities:
 * - adding messages (both single and multiple recipients) to the program
 * - adding replies to messages
 * - searching and returning messages based on their id
 * - checking if a message exists based on its id without modification
 * - getting various data about messages based off of their id
 * @author Peter Bilski, Khoa Pham
 * @see Message
 */
public class MessageManager {
    private final List<Message> allMessages;

    /**
     * a constructor that creates a UseCases.MessageManager object that stores a list of all messages and creates an
     * instance of the messageDatabase.
     */
    IMessageDatabase messageDatabase;
    public MessageManager(IMessageDatabase messageDatabase){
        this.messageDatabase = messageDatabase;
        allMessages = new ArrayList<>();
    }


    /**
     * Creates a method with a single recipient and adds it to the list of all messages
     * @param senderId the username of the sender
     * @param recipientId the username of the recipient
     * @param content the content of the message
     * @param convoID the id of the conversation that stores this method
     * @return the id of the message
     */
    public String sendMessageSingle(String senderId, String recipientId, String content, String convoID){
        Message message = new Message(senderId, recipientId, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    /**
     * Creates a method with multiple recipient and adds it to the list of all messages
     * @param senderId the username of the sender
     * @param recipientIds the usernames of the recipients
     * @param content the content of the message
     * @param convoID the id of the conversation that stores this method
     * @return the id of the message
     */
    public String sendMessageMulti(String senderId, List<String> recipientIds, String content, String convoID){
        Message message = new Message(senderId, recipientIds, content, convoID);
        allMessages.add(message);
        return message.getId();
    }

    /**
     * Adds a reply to the message specified by messageId
     * @param senderId the id of the sender of the reply
     * @param recipientIds the ids of the recipients
     * @param content the content of the reply message
     * @param messageId the message to which the reply is being made
     */
    public void addReply(String senderId, List<String> recipientIds, String content, String messageId){
        Message message = getMessage(messageId);
        if(message == null){
            return;
        }
        Message newMessage = new Message(senderId, recipientIds, content, message.getConvoID());
        message.setReply(newMessage.getId());
        allMessages.add(newMessage);
    }

    /**
     * Returns a message from allMessages based on its id
     * @param messageId the id of the message to be searched for
     * @return the message object if there is a corresponding message, null if there is not
     */
    public Message getMessage(String messageId){
        for(Message m: allMessages){
            if(m.getId().equals(messageId)){
                return m;
            }
        }
        return null;
    }

    /**
     * Returns the id of the reply to the message with the specified id
     * @param messageId the id of the message we are getting the reply for
     * @return the id of the reply
     */
    public String getReply(String messageId){
        if(getMessage(messageId) != null) {
            return getMessage(messageId).getReply();
        }
        else {
            return null;
        }
    }

    /**
     * Returns the username of the sender of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the username of the sender
     */
    public String getSender(String messageId){
        return getMessage(messageId).getSender();
    }

    /**
     * Returns the content of the sender of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the content of the message
     */
    public String getContent(String messageId){
        return getMessage(messageId).getContent();
    }

    /**
     * Returns the time of the message with the specified id
     * @param messageId the id of the message we are getting the sender of
     * @return the time of the message
     */
    public String getTime(String messageId){
        return getMessage(messageId).getTime().toString();
    }



    /**
     * Loads the data being stored by Message entities in the database into a Message entity and stores every
     * Message entity into the allMessages list which is a list of Message entities.
     *
     * @author Juan Yi Loke
     */
    public void loadFromDatabase() {
        List<Map<String, List<String>>> messageList = messageDatabase.getMessageList();

        for(Map<String, List<String>> message: messageList){
            List<String> messageInfo = message.get("messageInfo");
            String sender = messageInfo.get(1);
            List<String> ListOfRecipients = message.get("recipients");
            String content = messageInfo.get(2);
            String id = messageInfo.get(0);
            LocalDateTime localdateTime = LocalDateTime.parse(messageInfo.get(4));
            String convoID = messageInfo.get(5);
            String reply = messageInfo.get(3);
            Message newMessage = new Message(sender, ListOfRecipients, content, convoID);
            newMessage.setDateTime(localdateTime);
            newMessage.setReply(reply);
            newMessage.setID(id);
            allMessages.add(newMessage);
        }

    }

    /**
     * Stores the data being stored by the Message entities in the list allMessages in a List<String, List<String>>
     * data structure to be stored in the database system.
     *
     * @author Juan Yi Loke
     */
    public void saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList<>();

        for (Message message : allMessages) {

            String sender = message.getSender();
            List<String> ListOfRecipients = message.getRecipients();
            String content = message.getContent();
            String id= message.getId();
            String localDateTime = message.getTime().toString();
            String convoID = message.getConvoID();
            String reply = message.getReply();

            List<String> messageInfo = new ArrayList<>();

            messageInfo.add(id);
            messageInfo.add(sender);
            messageInfo.add(content);
            messageInfo.add(reply);
            messageInfo.add(localDateTime);
            messageInfo.add(convoID);

            Map<String, List<String>> resultingEvent = new HashMap<>();
            resultingEvent.put("messageInfo", messageInfo);
            resultingEvent.put("recipients", ListOfRecipients);

            resultingList.add(resultingEvent);
        }
        messageDatabase.saveMessageList(resultingList);
    }

}
