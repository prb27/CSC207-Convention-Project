@author Khoa Pham
This file offers important information regarding how to run the program.
The first time the master system is instantiated, an Organizer with username "Admin" and password "Admin" is created

Preconditions:
    - The client is not allowed to change username and password after creation
    - Event names, usernames are unique strings!
    - all IDs are stored as strings!
    - User can’t delete their accounts
    - User can’t remove people from their contacts list
    - Client must type exact integers as inputs when making choices
    - Only Attendee is allowed to signup for the program
    - The creation of Speaker Account and Organizer Account are taken place through Organizer Account
    - The first Organizer Account is available for anyone who runs the program at least once is the Admin Organizer


Supported functions:
    - Any Attendee/Organizer can send a message to all their contacts
    - Any Attendee/Organizer can send a message to some of their contacts
    - Any Attendee/Organizer can send a message to one of their contacts
    - Any Attendee/Organizer can sign up for a particular event
    - Any Attendee/Organizer can cancel their reservation for a particular event
    - Any Attendee/Organizer can see all the events
    - Any Attendee can see all their participating events
    - Client can create accounts with appropriate username and password of various user types and sign in to the
     conference
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
        - OrganizerMenu Process (19 options):
            CONFERENCE FUNCTIONS:
            1. List of all attendees in the conference
            2. List of all organizers in the conference
            3. List of all speakers in the conference
            4. Check if a speaker has an event at a certain time
            5. Create a new organizer account
            6: Create speaker account
            EVENT FUNCTIONS
            7: Add a room into the system
            8: Create new event or Schedule speaker for new event
            9: Change speaker for an event (Warning: once this option is chosen, the given event name will be removed.
                                           All attendees of the event should register again for this event )
            10: Change time of an event (Warning: once this option is chosen, the given event name will be removed,
             and a new event will be created at your chosen time. All attendees of the event should register
              again for this event
            11: Show events that I haven't signed up for
            12: Sign up for an event
            13: Cancel spot in an event
            14: See schedule of events signed up for
            MESSAGING FUNCTIONS: [Note: Since you are an organizer, you can send a message to any
                                  attendee/speaker/organizer]
            15: Send message to an attendee
            16: Send message to all attendees
            17: Send message to a speaker
            18: Send message to all speakers
            19: View Conversations
        - AttendeeMenu Process (7 options):
            EVENT FUNCTIONS:
            1: Available events to sign up for
            2: Sign up for an event
            3: Cancel spot in an event
            4: See schedule of event signed up for
            MESSAGING FUNCTIONS:
            5: Send message to an attendee
            6: Send message to a speaker of a talk
            7: View all conversations
        - SpeakerMenu Process (5 options):
            EVENT FUNCTIONS:
            1: View list of talks to be given
            MESSAGING FUNCTIONS:
            2: Message all attendees signed up for a talk
            3: Message all attendees attending multiple talks
            4: Message an attendee attending a talk
            5: View Conversations