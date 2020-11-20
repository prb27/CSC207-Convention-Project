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

        //deserialize here

        MasterSystem masterSystem = new MasterSystem();
        masterSystem.run();

        //serialize here
        try{
            FileOutputStream fout = new FileOutputStream("test.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(masterSystem);
            out.close();
            fout.close();
            System.out.println("Serialized data is saved in test.txt file");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}