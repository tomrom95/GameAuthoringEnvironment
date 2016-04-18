package facebookutil.test;

import facebookutil.JavaSocial;
import facebookutil.SocialType;
import facebookutil.applications.App;
import facebookutil.user.IUser;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class demos a working example of how the utility posts directly to Facebook
 * 
 *
 */
public class TestFacebook extends Application{
    
    private JavaSocial mySocial;
    private IUser myUser;
    
    private App myApp;

    @Override
    public void start (Stage stage) {
        mySocial = new JavaSocial();
        mySocial.loginUser(SocialType.Facebook);
        stage.setScene( testLogin());
        stage.show();
    }

    private Scene testLogin () {
        VBox box = new VBox(5);
        createFields (box);
        return new Scene(box, 500, 500);
    }

    private void createFields (VBox box) {
        box.getChildren().add(makePost());
        box.getChildren().add(makeNotify());
        box.getChildren().add(makeLogout());
    }

    private Node makeLogout () {
        Button button = new Button("Logout");
        button.setOnMouseClicked(e -> mySocial.logoutAll());
        return button;
    }

    private Node makePost () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Post");
        button.setOnMouseClicked(e -> post(field));
        box.getChildren().add(button);
        return box;
    }
    
    private Node makeNotify () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Notify");
        button.setOnMouseClicked(e -> notify(field));
        box.getChildren().add(button);
        return box;
    }

    private void notify (TextField field) {
        myApp = mySocial.getApplications().getAppByType(SocialType.Facebook);
        if (myApp == null) {
            System.out.println("Something's wrong");
            return;
        }
        myApp.notifyUsers(mySocial.getUsers(), field.getText());
    }

    private void post (TextField field) {
        myUser = mySocial.getActiveUser();
        //myApp = mySocial.getApplications().getAppByType(SocialType.Facebook);
        if (myUser == null) {
            System.out.println("Login first");
            return;
        }
        myUser.getProfiles().getActiveProfile().customPost(field.getText());
        //myApp.customPost(field.getText(), myApp);
    }

    public static void main (String[] args) {
        launch(args);
    }

}
