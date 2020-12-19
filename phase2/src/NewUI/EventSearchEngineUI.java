package NewUI;

import Controllers.EventsSearchEngine;

public class EventSearchEngineUI {
    private EventsSearchEngine eventsSearchEngine;
    public EventSearchEngineUI(EventsSearchEngine eventsSearchEngine){
        this.eventsSearchEngine = eventsSearchEngine;
    }

    public void option() {
        System.out.println("Enter the option number of your required action");
        System.out.println("1. View Events by Speaker");
        System.out.println("2. View Events by Start Time");
        System.out.println("3. View Events by Duration");
        System.out.println("4. View Events by Start Time and Duration");
        System.out.println("5. View Events by Speaker and Start Time");
        System.out.println("6. View Events by Speaker and Duration");
        System.out.println("7. View Events by Speaker, Start Time, and Duration");


    }
    public void promptForSpeakerUsername(){
        System.out.println("Please enter the speaker's username");
    }

    public void promptForStartTime(){
        System.out.println("Please enter the start time");
    }
    public void promptForDuration(){
        System.out.println("Please enter the duration");
    }
    public void seeEventsbySpeaker(String speakerName){
        System.out.println("Events by Speaker " + speakerName + ":");
        System.out.println(eventsSearchEngine.eventsWithSpeaker(speakerName));
    }
    public void seeEventsbyStartTime(String startTime){
        System.out.println("Events by Start Time " + startTime + ":");
        System.out.println(eventsSearchEngine.eventsAtStartTime(startTime));
    }
    public void seeEventsbyDuration(int duration){
        System.out.println("Events by Duration " + duration + ":");
        System.out.println(eventsSearchEngine.eventsForThisDuration(duration));

    }
    public void seeEventsbyStartTimeAndDuration(String startTime, int duration){
        System.out.println("Events by Start Time " + startTime + ", Duration "+ duration+":");
        System.out.println(eventsSearchEngine.eventsWithSpeakerAndDuration(startTime,duration));
    }
    public void seeEventsbySpeakerStartTime(String speakerName, String startTime){
        System.out.println("Events by Speaker "+speakerName+", Start Time "+startTime+":");
        System.out.println(eventsSearchEngine.eventWithSpeakerAndStartTime(speakerName, startTime));

    }
    public void seeEventsbySpeakerDuration(String speakerName, int duration){
        System.out.println("Events by Speaker "+speakerName+", Duration "+duration+":");
        System.out.println(eventsSearchEngine.eventsWithSpeakerAndDuration(speakerName, duration));

    }
    public void seeEventsbySpeakerStartTimeDuration(String speakerName,String startTime, int duration){
        System.out.println("Events by Speaker "+speakerName+", Start Time "+startTime+", Duration "+duration+":");
    }



}
