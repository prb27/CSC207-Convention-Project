package UseCases;

import Entities.Poll;

import java.util.ArrayList;
import java.util.List;

public class PollManager {

    private List<Poll> polls;

    public PollManager(List<Poll> polls){

        this.polls = polls;

    }


    public List<String> getPollsForEvent(String eventName){

        List<String> requiredPolls = new ArrayList<>();
        for(Poll poll: polls){
            if(poll.getEventName().equals(eventName)){
                requiredPolls.add(poll.getPollId());
            }
        }
        return requiredPolls;

    }


    private Poll getPoll(String pollId, String event){

        for(Poll poll: polls){
            if(poll.getPollId().equals(pollId) && poll.getEventName().equals(event)){
                return poll;
            }
        }
        return null;

    }


    public void voteInPoll(String username, String pollId, String event, int option){

        Poll poll = getPoll(pollId, event);
        if(poll != null) {
            List<String> alreadyVoted = poll.getAlreadyVoted();
            if (!alreadyVoted.contains(username) && option <= poll.getPollOptions().size()) {
                alreadyVoted.add(username);
                poll.setAlreadyVoted(alreadyVoted);
                List<Integer> currVotes = poll.getPollOptionVotes();
                int currVoteForOption = currVotes.get(option - 1);
                currVoteForOption = currVoteForOption + 1;
                currVotes.set(option - 1, currVoteForOption);
                poll.setPollOptionVotes(currVotes);
            }
        }

    }


    public boolean addNewPoll(String pollId, String eventName, String pollMessage, List<String> pollOptions){

        List<String> pollIds = getPollsForEvent(eventName);
        for(String pollId1: pollIds){
            if(pollId.equals(pollId1)){
                return false;
            }
        }
        Poll newPoll = new Poll(pollId, eventName, pollMessage, pollOptions, new ArrayList<Integer>(), new ArrayList<String>());
        polls.add(newPoll);
        return true;

    }


    public List<String> getAllPollsInfoForEvent(String username, String event) {

        List<String> requiredPolls = new ArrayList<>();
        List<String> polls = getPollsForEvent(event);
        Poll poll = null;
        int i = 1;
        int j;
        for (String pollId : polls) {
            poll = getPoll(pollId, event);
            j = 1;
            StringBuilder pollOptions = new StringBuilder("\n");
            for (String option : poll.getPollOptions()) {
                pollOptions.append(j);
                pollOptions.append(": ");
                pollOptions.append(option);
                pollOptions.append("\n");
                j++;
            }
            if (poll.getAlreadyVoted().contains(username)) {
                requiredPolls.add(i + " :\n" + "\nPoll ID: " + pollId + "\nEvent: " + event + "\nPoll Options: " + pollOptions + pollPercentPerOption(poll));
            }
            else{
                requiredPolls.add(i + " :\n" + "\nPoll ID: " + pollId + "\nEvent: " + event + "\nPoll Options: " + pollOptions);
            }
        }
        return requiredPolls;

    }


    private StringBuilder pollPercentPerOption(Poll poll){

        StringBuilder pollPercentages = new StringBuilder("\n");
        int percentage;
        int sum = 0;
        List<Integer> pollOptionVotes = poll.getPollOptionVotes();
        int j = 1;
        for(Integer i: pollOptionVotes){
            sum += i;
        }
        for(Integer i: pollOptionVotes){
            percentage = (i/sum)*100;
            pollPercentages.append((j + 1));
            pollPercentages.append(": ");
            pollPercentages.append(percentage);
            pollPercentages.append("\n");
        }
        return pollPercentages;

    }

}