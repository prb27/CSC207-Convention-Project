@author Khoa Pham
This file offers important information regarding how to run the program

Preconditions:
    - The client is not allowed to change username and password after creation
    - Event names, usernames are unique strings!
    - all IDs are stored as strings!
    - User can’t delete their accounts
    - User can’t remove people from their contacts list
    - Client must type exact integers as inputs when making choices


Supported functions:
    - Any Attendee/Organizer can send a message to all their contacts
    - Any Attendee/Organizer can send a message to some of their contacts
    - Any Attendee/Organizer can send a message to one of their contacts
    - Any Attendee/Organizer can sign up for a particular event
    - Any Attendee/Organizer can cancel their reservation for a particular event
    - Any Attendee/Organizer can see all the events
    - Any Attendee can see all their participating events
    - Client can create accounts with appropriate username and password of various user types and sign in to the conference
    - Any Organizer can create an event
    - Any Organizer can remove an event
    - Any Organizer can send a message to all user types
    - Any Organizer can send a message to a single user type
    - Any Speaker can send a message to all attendees of a talk
    - Any Speaker can send a message to all attendees of multiple talks
    - Any Attendee can send a message to another attendee

Instructions:
    - Login/Signup Process:
        Prompt if the user wants to login or signup and show the according menu
            - Login: enter username and password and wait for them to be checked
            - Signup: choose type of user to sign up (Organizer, Speaker, Attendee), then choose the username and
             password and wait to check if the username is taken (choose different username!)
    - According to ur type of users, u can see appropriate menus on what u can do (see supported functions)
    - There will be 3 types of menus that can be displayed based on the type of user.
        - Menu Selection Process
        - OrganizerMenu Process (10 options):
            "1: Create speaker account"
            "2: Add a room into the system"
            "3: Schedule speakers to speak in a room"
            "4: Available events to sign up for"
            "5: Cancel spot in an event"
            "6: See schedule of event signed up for"
            "7: Send message to an attendee"
            "8: Send message to all attendees"
            "9: Send message to a speaker"
            "10: Send message to all speakers"
        - AttendeeMenu Process (5 options):
            "1: Available events to sign up for"
            "2: Cancel spot in an event"
            "3: See schedule of event signed up for"
            "4: Send message to an attendee"
            "5: Send message to a speaker of a talk"
        - SpeakerMenu Process (3 options):
            "1: View list of talks to be given"
            "2: Message all attendees signed up for a talk"
            "3: Message all attendees signed up for all talks"
            "4: Message an attendee attending a talk"
            "5: View all conversations"
