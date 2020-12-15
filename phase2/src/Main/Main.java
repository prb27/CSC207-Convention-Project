package Main;

import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{




    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/SignUpMenuView.fxml"));
        AnchorPane signUpPage = (AnchorPane) loader.load();
        Scene scene = new Scene(signUpPage);

        primaryStage.setTitle("Tech-Conference");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * main method to run the program
     * @param args: string arguments for main method
     */
    public static void main(String[] args) {
        ProgramGenerator programGenerator = new ProgramGenerator();
        MasterSystem masterSystem = programGenerator.readFromDatabase();
        masterSystem.run();
        launch(args); // for javafx launching application
    }

}