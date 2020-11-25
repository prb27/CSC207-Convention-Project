package UseCases;

import Entities.Event;
import Entities.Events.MultiSpeakerEvent;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MultiEventManager implements Serializable {
    private ArrayList<MultiSpeakerEvent> MultiSpeakerEventList = new ArrayList<>();

    public ArrayList<MultiSpeakerEvent> getMultiSpeakerEventList() {
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

    public ArrayList<String> getSpeakerEvent(String eventName){
        MultiSpeakerEvent event = getEvent(eventName);
        return event.getSpeakerNames();
    }



}
