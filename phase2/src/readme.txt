@author Juan Yi Loke


Prerequisite:
- mongo-java-driver-3.12.17.jar

Description:
Phase 2 of our program extends Phase 1 with the following extensions:

    - the mandatory extensions (Ashwin, Khoa, Arib)

    - 3 optional extensions:
        1) "Add additional constraints to the scheduling for various types of events (e.g. technology requirements for
        presentations, tables vs rows of chairs). When organizers are creating events, they can see a suggested list
        of rooms that fit the requirements of their event." (Ashwin, Arib)

        2) "Alternatively, if you just want the program to print the schedule to the screen,  then users should be able
        to request a schedule by at least three of: day, by speaker, by time (all 3-4 pm talks on all days), or just
        the ones they have signed up for, or "liked" events (where you have to enable users to "like" events)." (Ashwin)

        3) "Use a database to store the information from your program through specific gateway class(es) so that you do
        not violate Clean Architecture and still have an Entity layer." (Johnny, Akshat)

    - 1 big original feature:
        1) Polling system (Ashwin)

    Refactoring of the code to use Text UI instead of GUI was done by:
    Ashwin, Johnny, Khoa

    Text UI extensions with the right menus and etc, along with the bug fixes will be done by:
        Known Bugs:
        - The current TextUI does something weird where it shows the menu options twice for any user whenever u want to
        make an option. It's not really program breaking because you can still make your choices that brings you
        to the functionalities you can do, but it's just weird because you have to choose it twice.

        To Implement:
        - The remaining Text UI menus for the Search Engine Ashwin created. I quote Ashwin for exact requirements:
        "The EventsSearchEngine stuff should work without anyone logging in. i.e. I want 3 options to be shown when
        the programs runs. log in and sign up are already there. I want option 3 to be the search engine. It need not
        depend on any User. So When i run the program , I should be able to view events as per search engine functions
        without having to log in to an account.

