package Controllers;

import UseCases.EventManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods that perform actions like searching for events with certain parameters (eg. Speaker's username).
 * @author Ashwin
 */
public class EventsSearchEngine {

    private final EventManager eventManager;

    public EventsSearchEngine(EventManager eventManager){

        this.eventManager = eventManager;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for all events
     * @return List</String>
     */
    public List<String> allEvents(){

        return new ArrayList<>(eventManager.getAllEventTitles());
    }

    /**
     * Returns a List of Strings that provide the event titles
     * for all events
     * @return List</String>
     */
    public List<String> allEventsUnformatted(){
        return eventManager.getAllEventTitles();
    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName>
     * @param speakerName the speaker's username (param_type: String)
     * @return List</String>
     */
    public List<String> eventsWithSpeaker(String speakerName){

        List<String> eventsWithThisSpeaker = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getSpeakerEvent(event).contains(speakerName)){
                eventsWithThisSpeaker.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsWithThisSpeaker;

    }

    /**
     * Returns a List of Strings that provide the event titles
     * for events with speaker </speakerName>
     * @param speakerName the speaker's username (param_type: String)
     * @return List</String>
     */
    public List<String> eventsWithSpeakerUnformatted(String speakerName){

        List<String> eventsWithThisSpeaker = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getSpeakerEvent(event).contains(speakerName)){
                eventsWithThisSpeaker.add(event);
            }
        }
        return eventsWithThisSpeaker;

    }


    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with start time </startTime>
     * @param startTime the start time for the searched event (param_type: String)
     * @return List</String>
     */
    public List<String> eventsAtStartTime(String startTime){

        List<String> eventsAtThisStartTime = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getStartTime(event).equals(startTime)){
                eventsAtThisStartTime.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsAtThisStartTime;

    }

    /**
     * Returns a List of Strings that provide the event titles
     * for events with start time </startTime>
     * @param startTime the start time for the searched event (param_type: String)
     * @return List</String>
     */
    public List<String> eventsAtStartTimeUnformatted(String startTime){

        List<String> eventsAtThisStartTime = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getStartTime(event).equals(startTime)){
                eventsAtThisStartTime.add(event);
            }
        }
        return eventsAtThisStartTime;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events that are duration </duration> hours long.
     * @param duration the duration of the events being search for (param_type: int)
     * @return List</String>
     */
    public List<String> eventsForThisDuration(int duration){

        List<String> eventsForThisDuration = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getDuration(event) == duration){
                eventsForThisDuration.add("Event Title: " + event + "\nTime: " + eventManager.getStartTime(event) + "(" + eventManager.getDuration(event) + " hours)" + "\nRoom: " + eventManager.getRoomNumber(event) + "\nSpeaker: " + eventManager.getSpeakerEvent(event) + "\n");
            }
        }
        return eventsForThisDuration;

    }

    /**
     * Returns a List of Strings that provide the event titles
     * for events that are duration </duration> hours long.
     * @param duration the duration of the events being search for (param_type: int)
     * @return List</String>
     */
    public List<String> eventsForThisDurationUnformatted(int duration){

        List<String> eventsForThisDuration = new ArrayList<>();
        for(String event: eventManager.getAllEventTitles()){
            if(eventManager.getDuration(event) == duration){
                eventsForThisDuration.add(event);
            }
        }
        return eventsForThisDuration;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> and duration </duration>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
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

    /**
     * Returns a List of Strings that provide the event titles
     * for events with speaker </speakerName> and duration </duration>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventsWithSpeakerAndDurationUnformatted(String speakerName, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDurationUnformatted(duration);
        List<String> eventsWithThisSpeaker = eventsWithSpeakerUnformatted(speakerName);
        for(String event: eventsWithThisSpeaker){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> at start time </time>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @return List</String>
     */
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

    /**
     * Returns a List of Strings that provide the event titles
     * for events with speaker </speakerName> at start time </time>
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @return List</String>
     */
    public List<String> eventWithSpeakerAndStartTimeUnformatted(String speakerName, String time){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsAtThisStartTime = eventsAtStartTimeUnformatted(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeakerUnformatted(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events at start time </time> and duration </duration>
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
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

    /**
     * Returns a List of Strings that provide the event titles
     * for events at start time </time> and duration </duration>
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventsWithStartTimeAndDurationUnformatted(String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDurationUnformatted(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTimeUnformatted(time);
        for(String event: eventsAtThisStartTime){
            if(eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

    /**
     * Returns a List of Strings that provide information about the event title, start time, duration, roomId and Speaker names
     * for events with speaker </speakerName> at start time </time> and duration </duration> hours.
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
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

    /**
     * Returns a List of Strings that provide the event titles
     * for events with speaker </speakerName> at start time </time> and duration </duration> hours.
     * @param speakerName username of the speaker for the searched event (param_type: String)
     * @param time start time of the searched event (param_type: String)
     * @param duration duration of the searched event (param_type: int)
     * @return List</String>
     */
    public List<String> eventWithSpeakerNameStartTimeAndDurationUnformatted(String speakerName, String time, int duration){

        List<String> requiredEvents = new ArrayList<>();
        List<String> eventsForThisDuration = eventsForThisDurationUnformatted(duration);
        List<String> eventsAtThisStartTime = eventsAtStartTimeUnformatted(time);
        List<String> eventsWithThisSpeaker = eventsWithSpeakerUnformatted(speakerName);
        for(String event: eventsAtThisStartTime){
            if(eventsWithThisSpeaker.contains(event) && eventsForThisDuration.contains(event)){
                requiredEvents.add(event);
            }
        }
        return requiredEvents;

    }

}