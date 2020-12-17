package Controllers;

import UseCases.PollManager;
import UseCases.SpeakerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PollController {

    private SpeakerManager speakerManager;
    private PollManager pollManager;

    public PollController(PollManager pollManager, SpeakerManager speakerManager){
        this.pollManager = pollManager;
        this.speakerManager = speakerManager;
    }

    public void runPollFunctionality(String username){

        Scanner scanner = new Scanner(System.in);
        if(speakerManager.isSpeaker(username)){
            //print this menu (I want it to be in this exact same order and number):
            //Enter the option number of your required action
            //1. View poll information for all polls with an event-polling-passcode
            //2. Create new poll
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    // Ask for the event-polling-passcode as provided by the speaker
                    String eventPasscode = scanner.nextLine();
                    List<String> formattedPollInfo = pollManager.getAllPollsInfoForEvent(username, eventPasscode);
                    // Print the List of strings I send as a parameter
                    return;
                }
                case "2":{
                    //Ask for a new Poll id, event-polling-passcode, poll question/message and number of choices they want to give for answers
                    String pollId = scanner.nextLine();
                    String eventPasscode = scanner.nextLine();
                    String pollMessage = scanner.nextLine();
                    int numberOfPollOptions = scanner.nextInt();
                    List<String> pollOptions = new ArrayList<>();
                    List<String> alreadyVoted = new ArrayList<>();
                    alreadyVoted.add(username);
                    //Ask them to enter the options [Note, they shouldn't have the "enter key" or "\n" as a part of the string]
                    //warn them accordingly
                    for (int i=0; i<numberOfPollOptions; i++){
                        pollOptions.add(scanner.nextLine());
                    }
                    boolean result;
                    result = pollManager.addNewPoll(pollId, eventPasscode, pollMessage, pollOptions, alreadyVoted);
                    if(result){
                        //I want to call a method that prints successful
                    }
                    else{
                        //I want to call a method that says "Something went wrong" [If we are being general]
                        //If want to make it more professional I want a method saying "Poll ID already exists for this event-polling-passcode"
                    }
                    return;
                }
                default:{
                    //Print "Please enter a valid option"
                }
            }
        }
        else{
            // Ask for the event-polling-passcode as provided by the speaker
            String eventPasscode = scanner.nextLine();
            List<String> formattedPollInfo = pollManager.getAllPollsInfoForEvent(username, eventPasscode);
            // Print the List of strings I send as a parameter
            // Print "Do you want to vote in a poll? (Enter "YES" if you want to vote. Enter anything else if you don't want to exit this menu)"
            String reply = scanner.nextLine();
            if(reply.equals("YES")){
                // Print "Please enter the Poll Id of the poll in which you want to vote (Note: If you can see the result of the poll, you have already voted in the poll)"
                String pollId = scanner.nextLine();
                // Print "Enter the option number that you want to vote for"
                int option = scanner.nextInt();
                pollManager.voteInPoll(username, pollId, eventPasscode, option);
                // Print "Successful"
                // Print "To view the result, please exit the menu and re-enter"
            }
        }
    }

}