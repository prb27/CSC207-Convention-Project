package UseCases;

import Entities.Event;
import Entities.Message;
import Gateways.IEventDatabase;
import Gateways.IMessageDatabase;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class MessageManager implements Serializable {
    private List<Message> allMessages;

    /**
     * Constructor for a UseCases.MessageManager, initializes the list of messages to be empty
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
     * Delete a message with given id
     * @author Khoa Pham
     * @param message: the to-be-deleted message
     * @return the id of the message
     */
    public void deleteMessage(String message) {
        allMessages.remove(this.getMessage(message));
    }

    /**
     * Adds a reply to the message specified by messageId
     * @param senderId the id of the sender of the reply
     * @param recipientIds the ids of the recipients
     * @param content the content of the reply message
     * @param messageId the message to which the reply is being made
     * @return true if the reply was succesfully added, false otherwise
     */
    public boolean addReply(String senderId, List<String> recipientIds, String content, String messageId){
        Message message = getMessage(messageId);
        if(message == null){
            return false;
        }
        Message newMessage = new Message(senderId, recipientIds, content, message.getConvoID());
        message.setReply(newMessage.getId());
        allMessages.add(newMessage);
        return true;
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
     * Check if a message with ID exists in allMessages
     * @author Khoa Pham
     * @param messageId the id of the message to be searched for
     * @return boolean
     * true -- message exits
     * false -- message doesn't exist
     */
    public boolean messageExists(String messageId){
        return allMessages.contains(this.getMessage(messageId));
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




    public void loadFromDatabase() {
        List<Map<String, List<String>>> messageList = messageDatabase.getMessageList();

        for(Map<String, List<String>> message: messageList){
            String sender = message.get("sender").get(0);
            List<String> ListOfRecipients = message.get("listOfRecipients");
            String content = message.get("content").get(0);
            String id = message.get("id").get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime localdateTime = LocalDateTime.parse(message.get("localDateTime").get(0), formatter);
            String convoID = message.get("convoID").get(0);
            String reply = message.get("reply").get(0);
            Message newMessage = new Message(sender, ListOfRecipients, content, convoID);
            newMessage.setDateTime(localdateTime);
            newMessage.setReply(reply);
            newMessage.setID(id);
            allMessages.add(newMessage);
        }

    }

    public List<Map<String, List<String>>> saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList();

        for (Message message : allMessages) {

            String sender = message.getSender();
            List<String> ListOfRecipients = message.getRecipients();
            String content = message.getContent();
            String id= message.getId();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime localdateTime = message.getTime();
            String dateTime = localdateTime.format(formatter);
            String convoID = message.getConvoID();
            String reply = message.getReply();


            List<String> senderTemp = new ArrayList<>();
            List<String> contentTemp = new ArrayList<>();
            List<String> idTemp = new ArrayList<>();
            List<String> dateTimeTemp = new ArrayList<>();
            List<String> convoIDTemp = new ArrayList<>();
            List<String> replyTemp = new ArrayList<>();

            senderTemp.add(sender);
            contentTemp.add(content);
            idTemp.add(id);
            dateTimeTemp.add(dateTime);
            convoIDTemp.add(convoID);
            replyTemp.add(reply);


            Map<String, List<String>> resultingEvent = new HashMap();
            resultingEvent.put("sender", senderTemp);
            resultingEvent.put("listOfRecipients", ListOfRecipients);
            resultingEvent.put("content", contentTemp);
            resultingEvent.put("id", idTemp);
            resultingEvent.put("convoID", convoIDTemp);
            resultingEvent.put("reply", replyTemp);
            resultingEvent.put("localDateTime", dateTimeTemp);

            resultingList.add(resultingEvent);
        }
        return resultingList;
    }

}
