package Controllers;

import UseCases.EventManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EventsSearchEngine implements Serializable {

    private final EventManager eventManager;

    public EventsSearchEngine(EventManager eventManager){

        this.eventManager = eventManager;

    }

    public List<String> eventsWithSpeaker(String speakerName){

        List<String> eventsWithThisSpeaker = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getSpeakerEvent(event).contains(speakerName)){
                eventsWithThisSpeaker.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsWithThisSpeaker;

    }


    public List<String> eventsAtStartTime(String startTime){

        List<String> eventsAtThisStartTime = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getStartTime(event).equals(startTime)){
                eventsAtThisStartTime.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsAtThisStartTime;

    }


    public List<String> eventsForThisDuration(int duration){

        List<String> eventsForThisDuration = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getDuration(event) == duration){
                eventsForThisDuration.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsForThisDuration;

    }


    public List<String> eventsWithSpeakerAndDuration(String speakerName, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsWithThisSpeaker){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    public List<String> eventWithSpeakerAndStartTime(String speakerName, String time){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    public List<String> eventsWithStartTimeAndDuration(String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        for(String event: eventsAtThisStartTime){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    public List<String> eventWithSpeakerNameStartTimeAndDuration(String speakerName, String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDuration(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTime(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeaker(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event) && eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

}