package UseCases;

import Entities.Events.MultiSpeakerEvent;
import Gateways.Interfaces.IMultiEventDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Map<String, List<String>>> saveToDatabase() {

        List<Map<String, List<String>>> resultingList = new ArrayList();

        for (MultiSpeakerEvent multiSpeakerEvent : MultiSpeakerEventList) {

            String eventName = multiSpeakerEvent.getEventName();
            List<String> speakerNames = multiSpeakerEvent.getSpeakerNames();
            String eventTime = multiSpeakerEvent.getEventTime();
            String roomNumber = multiSpeakerEvent.getRoomNumber();
            List<String> attendeeList = multiSpeakerEvent.getAttendeeList();

            List<String> eventNameTemp = new ArrayList<>();
            List<String> eventTimeTemp = new ArrayList<>();
            List<String> roomNumberTemp = new ArrayList<>();


            eventNameTemp.add(eventName);
            eventTimeTemp.add(eventTime);
            roomNumberTemp.add(roomNumber);

            Map<String, List<String>> resultingEvent = new HashMap();
            resultingEvent.put("eventName", eventNameTemp);
            resultingEvent.put("eventTime", eventTimeTemp);
            resultingEvent.put("roomNumber", roomNumberTemp);
            resultingEvent.put("speakerNames", speakerNames);
            resultingEvent.put("attendeeList", attendeeList);

            resultingList.add(resultingEvent);
        }
        return resultingList;
    }



}
