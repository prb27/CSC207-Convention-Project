import java.util.Scanner;

public class TextUI {
    public static void main(String[] args) {

    }
    public void landingmenu(){
        Scanner input = new Scanner(System. in);
        System.out.println("Welcome! What would you like to do?");
        System.out.println("1: log in");
        System.out.println("2: sign up");
        int choice = input.nextInt();
        while (choice > 2 || choice < 1){
            System.out.println("Please choose either 1 or 2.");
            choice = input.nextInt();
        }
    }

    public void loginmenu(){
        // collects the username and password from the user and returns it
        Scanner input = new Scanner(System. in);
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        System.out.println("Please enter your password:");
        String password = input.nextLine();
        // return the username and password
    }

    public void signupmenu() {
        Scanner input = new Scanner(System. in);
        System.out.println("What type of user are you?");
        System.out.println("1: Organizer");
        System.out.println("2: Speaker");
        int choice = input.nextInt();
        while (choice > 2 || choice < 1){
            System.out.println("Please choose either 1 or 2.");
            choice = input.nextInt();
        }
    }
}

// For Phase 1, your User Interface (UI) will be text only.
// This involves printing menu items and user prompts on the screen
// and reading the user's response from the keyboard.

// What are potential menu items to print?
// What are user prompts that we need?
// What are we going to read from user response?


//
