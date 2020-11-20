import java.io.*;

public class ProgramGenerator {


    public MasterSystem readFromFile(String filePath) {
        try {
            InputStream file = new FileInputStream(filePath + ".ser");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            MasterSystem masterSystem = (MasterSystem) input.readObject();
            input.close();

            return masterSystem;

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Cannot read from file");
            return new MasterSystem();
        }
    }


        public void saveToFile(MasterSystem object, String filePath){
            try {
                OutputStream file = new FileOutputStream(filePath + ".ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);

                output.writeObject(object);
                output.close();

                System.out.println("Data successfully stored in " + filePath + ".ser");

            } catch (IOException ex) {
                System.out.println("IOException while saving data");
                ex.printStackTrace();
            }
        }

}



// Note, that serialization is for objects.