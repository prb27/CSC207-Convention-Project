//package scratch;
//
//import java.util.ArrayList;
//
///**
// * this class controls and performs various Attendee's functionalities according to user's commands
// * these functionalities include:
// *      - allow any Attendee to send a message to all their contacts
// *      - allow any Attendee to send a message to some of their contacts
// *      - allow any Attendee to send a message to one of their contacts
// *      - allow any Attendee to signup for a particular event
// *      - allow any Attendee to cancel their reservation for a particular event
// *      - allow any Attendee to see all the events
// *      - allow any Attendee to see all their participating events
// * @author Khoa Pham
// * @see AttendeeManager
// * @see EventManager
// * @see MessageSystem
// */
//public class AttendeeMenu {
//    private final AttendeeManager attendeeManager = new AttendeeManager();
//    private EventManager eventManager;
//    private MessageSystem messageSystem;
//
//
//    /**
//     * constructor for an scratch.AttendeeMenu
//     * initializing the two fields (eventManager and messageSystem)
//     * @param messageSystem: the program's messageSystem employed to perform
//     *                     attendee's functionalities relating to messages (param_type: MessageSystem)
//     * @param eventManager: the program's eventManager employed to perform
//     *                    attendee's functionalities relating to events (param_type: EventManager)
//     * @return an scratch.AttendeeMenu object
//     */
//    public AttendeeMenu(MessageSystem messageSystem, EventManager eventManager) {
//        this.eventManager = eventManager;
//        this.messageSystem = messageSystem;
//    }
//
////    /**
////     * allow an Attendee to send a message with a given content to all of their contacts
////     * call messageSystem to perform!
////     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
////     * @param content: the actual message text to be sent (param_type: String)
////     * @return void
////     */
////    public void messAllContacts(String a, String content) {
////        // check <a> ??
////        Attendee attendee = attendeeManager.getAttendee(a);
////        messageSystem.multiMessage(a, attendee.getContacts(), content);
////    }
////
////    /**
////     * allow an Attendee to send a message with a given content to some of their contacts
////     * call messageSystem to perform!
////     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
////     * @param receivers: the list of the message's recipients (param_type: ArrayList<String>)
////     * @param content: the actual message text to be sent (param_type: String)
////     * @return void
////     */
////    public void messSome(String a, ArrayList<String> receivers, String content) {
////        messageSystem.multiMessage(a, receivers, content);
////    }
////
////    /**
////     * allow an Attendee to send a message with a given content to one of their contacts
////     * call messageSystem to perform!
////     * @param a: the username of an Attendee who will be the sender of a message (param_type: String)
////     * @param b: the recipient of the message (param_type: String)
////     * @param content: the actual message text to be sent (param_type: String)
////     * @return void
////     */
////    public void messOne(String a, String b, String content) {
////        messageSystem.singleMessage(a, b, content);
////    }
//
////    /**
////     * allow an Attendee to signup to a particular event
////     * call eventManager to perform!
////     * @param attendee: the username of an Attendee who will attend this event (param_type: String)
////     * @param event: the intended event (param_type: String)
////     * @return void
////     */
////    public void signUp(String attendee, String event) {
////        ArrayList<String> participatingEvents = attendeeManager.getAttendee(attendee).getEventsAttending();
////        attendeeManager.addAttendingEvent(attendee, event);
////        eventManager.reserveAttendee(event, attendee, participatingEvents);
////    }
////
////    /**
////     * allow an Attendee to cancel their reservation to a particular event
////     * call eventManager to perform!
////     * @param attendee: the username of an Attendee who wants to cancel
////     *                reservation for an event (param_type: String)
////     * @param event: the intended event (param_type: String)
////     * @return void
////     */
////    public void cancel(String attendee, String event) {
////        ArrayList<String> participatingEvents = attendeeManager.getAttendee(attendee).getEventsAttending();
////        attendeeManager.removeAttendingEvent(attendee, event);
////        eventManager.removeAttendee(event, attendee, participatingEvents);
////    }
////
////    /**
////     * return the list of all events
////     * call eventManager to perform!
////     * @return ArrayList<Event> events
////     */
////    public ArrayList<Event> seeAllEvents() {
////        return eventManager.getEventList();
////    }
////
////    /**
////     * allow an Attendee to see the list of all their participating events
////     * call eventManager to perform!
////     * @param attendee: the username of an Attendee whose list of
////     *                participating events is returned (param_type: String)
////     * @return ArrayList<Event> events
////     */
////    public ArrayList<Event> seeParticipatingEvents(String attendee) {
////        ArrayList<String> eventIds = attendeeManager.getAttendee(attendee).getEventsAttending();
////        ArrayList<Event> eventObjs = new ArrayList<>();
////        for (String id: eventIds) {
////            eventObjs.add(eventManager.getEvent(id));
////        }
////        return eventObjs;
////    }
////
////    /**
////     * enroll an Attendee with <username> to an Event <eventName>
////     * If event with <eventName> and Attendee with <username> exist,
////     * check room capacity and enrol that Attendee to that Event
////     * Notes:
////     * ADE - Attendee doesn't exist
////     * EDE - Event doesn't exist
////     * EFC - Event at full capacity
////     * @author Khoa Pham
////     * @param username: the username of an Attendee to be enrolled in an event (param_type: String)
////     * @param eventName: the intended event (param_type: String)
////     * @return String
////     */
////    public String enrolAttendeeInEvent(String username, String eventName) {
////        // ent.
////
////        if (attendeeManager.isAttendee(username) && eventManager.getEvent(eventName) != null) {
////            String roomId = eventManager.getEvent(eventName).getRoomNumber();
////            int capacity = roomManager.getCapacityOfRoom(roomId);
////            ArrayList<String> attendeesOfEvent = eventManager.getEvent(eventName).getAttendeeList();
////            if (attendeesOfEvent.size() < capacity) {
////                String erMessage = eventManager.reserveAttendee(eventName, username);
////                if (erMessage.equals("YES")) {
////                    attendeeManager.addAttendingEvent(username, eventName);
////                    return "YES";
////                }
////                return erMessage;
////            }
////            return "EFC";
////        }
////        return "ADE/EDE";
////    }
//}
