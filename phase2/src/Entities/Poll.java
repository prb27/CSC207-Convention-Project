package Entities;

import java.util.HashMap;
import java.util.List;

public class Poll {

    private final String pollId;
    private final String eventPasscode;
    private final String pollMessage;
    private final List<String> pollOptions;
    private List<Integer> pollOptionVotes;
    private List<String> alreadyVoted;


    public Poll(String pollId, String eventPasscode, String pollMessage, List<String> pollOptions, List<Integer> pollOptionVotes, List<String> alreadyVoted){

        this.pollId = pollId;
        this.eventPasscode = eventPasscode;
        this.pollMessage = pollMessage;
        this.pollOptions = pollOptions;
        this.pollOptionVotes = pollOptionVotes;
        this.alreadyVoted = alreadyVoted;

    }


    public String getPollMessage(){
        return pollMessage;
    }


    public List<String> getPollOptions(){
        return pollOptions;
    }


    public List<String> getAlreadyVoted(){
        return alreadyVoted;
    }


    public void setAlreadyVoted(List<String> alreadyVoted){
        this.alreadyVoted = alreadyVoted;
    }


    public String getEventPasscode(){
        return eventPasscode;
    }


    public String getPollId(){
        return pollId;
    }

    public List<Integer> getPollOptionVotes(){
        return pollOptionVotes;
    }

    public void setPollOptionVotes(List<Integer> pollOptionVotes){
        this.pollOptionVotes = pollOptionVotes;
    }

}
