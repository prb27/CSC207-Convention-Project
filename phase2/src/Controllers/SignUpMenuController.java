package Controllers;

import Main.Main;
import Presenters.SignUpMenuPresenter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class SignUpMenuController {

    private Main main1;
    private AccountHandler accountHandler;

    public void returnToLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(SignUpMenuPresenter.class.getResource("/LoginMenuView.fxml"));
        AnchorPane loginPage = (AnchorPane) loader.load();
        Scene loginPageScene = new Scene(loginPage);

        main1.getStage().setScene(loginPageScene);
    }




}
