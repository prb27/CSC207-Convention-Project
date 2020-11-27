import Controllers.MasterSystem;
import Gateways.ProgramGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {


    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/SignUpMenu.fxml"));
        AnchorPane loginPage = (AnchorPane) loader.load();
        Scene scene = new Scene(loginPage);

        stage.setTitle("Tech-Conference");
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage(){
        return stage;
    }

    /**
     * main method to run the program
     * @param args: string arguments for main method
     */
    public static void main(String[] args) {
        ProgramGenerator programGenerator = new ProgramGenerator();
        MasterSystem masterSystem = programGenerator.readFromFile("conference_system");
        masterSystem.run();
        launch(args); // for javafx launching application
    }

}