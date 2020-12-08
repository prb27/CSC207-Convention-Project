package UseCases;

import Entities.Event;
import Entities.Events.MultiSpeakerEvent;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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



}
