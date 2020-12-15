package Main;

import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import Presenters.SignUpMenuPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{




    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/UI/SignUpMenuView.fxml"));
        AnchorPane signUpPage = (AnchorPane) loader.load();

        ProgramGenerator programGenerator = new ProgramGenerator();
        MasterSystem masterSystem = programGenerator.readFromDatabase();
        masterSystem.run();
        SignUpMenuPresenter signUpMenuPresenter = loader.getController();
        signUpMenuPresenter.setMasterSystem(masterSystem);
        Scene scene = new Scene(signUpPage);
        primaryStage.setTitle("Tech-Conference");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            programGenerator.readToDatabase();
        });
    }


    /**
     * main method to run the program
     * @param args: string arguments for main method
     */
    public static void main(String[] args) {
        // Don't need this code here. Program generator and master system are already being created
        // in the start method above.
//        ProgramGenerator programGenerator = new ProgramGenerator();
//        MasterSystem masterSystem = programGenerator.readFromDatabase();
//        masterSystem.run();
        launch(args); // for javafx launching application
    }

}