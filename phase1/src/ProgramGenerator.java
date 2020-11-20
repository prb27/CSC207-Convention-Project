import java.io.*;

public class ProgramGenerator {


    public Object readFile(String filePath) {
        try {
            FileInputStream fileIn = new FileInputStream(filePath + ".txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Object object = (Object) in.readObject();

            in.close();
            fileIn.close();

            return object;

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }


        public void savetoFile (String filePath, Object object){
            try {
                FileOutputStream fout = new FileOutputStream(filePath + ".txt");
                ObjectOutputStream out = new ObjectOutputStream(fout);

                out.writeObject(object);

                out.close();
                fout.close();

                System.out.println("Serialized data is saved in test.txt file");

            } catch (IOException ex) {
                System.out.println("IOException is caught");
            }
        }
    }

}



// Note, that serialization is for objects.