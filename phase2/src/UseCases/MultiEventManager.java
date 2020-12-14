package UseCases;

import Entities.Event;
import Entities.Events.MultiSpeakerEvent;
import Entities.Message;
import Gateways.IMultiEventDatabase;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiEventManager implements Serializable {
    private List<MultiSpeakerEvent> MultiSpeakerEventList = new ArrayList<>();

    public List<MultiSpeakerEvent> getMultiSpeakerEventList() {
        return MultiSpeakerEventList;
    }

    private MultiSpeakerEvent getEvent(String eventName){

        for(MultiSpeakerEvent event: MultiSpeakerEventList){
            if(event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null;

    }
    public boolean isEvent(String eventName){
        return MultiSpeakerEventList.contains(getEvent(eventName));
    }

    public List<String> getSpeakerEvent(String eventName){
        MultiSpeakerEvent event = getEvent(eventName);
        return event.getSpeakerNames();
    }

    IMultiEventDatabase multiEventDatabase;
    public MultiEventManager(IMultiEventDatabase multiEventDatabase){
        this.multiEventDatabase = multiEventDatabase;
    }


    public void loadFromDatabase() {
        List<Map<String, List<String>>> multiSpeakerEventList = multiEventDatabase.();

        for(Map<String, List<String>> multiSpeakerEvent: multiSpeakerEventList){
            String eventName = multiSpeakerEvent.get("eventName").get(0);
            List<String> speakerNames = multiSpeakerEvent.get("speakerNames");
            String eventTime = multiSpeakerEvent.get("eventTime").get(0);
            String roomNumber = multiSpeakerEvent.get("roomNumber").get(0);
            List<String> attendeeList = multiSpeakerEvent.get("attendeeList");

            MultiSpeakerEvent newMultiSpeakerEvent =
                    new MultiSpeakerEvent(eventName, speakerNames, eventTime, roomNumber, attendeeList);

            MultiSpeakerEventList.add(newMultiSpeakerEvent);
        }

    }



}
