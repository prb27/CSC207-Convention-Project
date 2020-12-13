package Controllers;

public class ConversationMenuController {

    public String conversationInformation;
    public ConversationMenuController(){
        this.conversationInformation = "";
    }
    public void setConversationInformation(String information){
        this.conversationInformation = information;
    }
    public String getConversationInformation(){
        return conversationInformation;
    }
}
