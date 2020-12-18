package NewUI;

import java.util.List;

public class MessagingMenuPresenter {

    public MessagingMenuPresenter(){

    }
    public void promptMessageToSend(){
        System.out.println("Please enter the message that you want to send");
    }
    public void promptToReply(){
        System.out.println("Enter \"r\" to reply in this conversation." +
                " [Any other input will exit this menu]");
    }
    public void promptConvoNumber(){
        System.out.println("Choose a conversation number");
    }
    public void presentMessageInConvo(List<String> messagesInConvo) {
        for (String s : messagesInConvo) {
            System.out.println(s);
        }
    }

    public void noConvo(){
        System.out.println("You have no conversations");
    }
}
