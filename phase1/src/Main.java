import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    /**
     * main method to run the program
     * @param args: string arguments for main method
     */
    public static void main(String[] args) {
        ProgramGenerator programGenerator = new ProgramGenerator();
        MasterSystem masterSystem = programGenerator.readFromFile("conference_system");
        masterSystem.run();
    }
}