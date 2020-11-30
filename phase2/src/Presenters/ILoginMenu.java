package Presenters;

public interface ILoginMenu {

    String getUsername();
    void setUsername();

    String getPassword();
    String setPassword();

    interface LoginMenuViewListener{
        void onLoginButtonClicked();
    }

    void invalidUser();
}
