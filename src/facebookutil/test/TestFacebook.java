package facebookutil.test;

import facebookutil.JavaSocial;
import facebookutil.SocialType;
import facebookutil.user.IUser;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class demos a working example of how the utility posts directly to Facebook
 * 
 *
 */
public class TestFacebook extends Application{
    
    private JavaSocial mySocial;
    private IUser myUser;

    @Override
    public void start (Stage stage) {
        mySocial = new JavaSocial();
        mySocial.loginUser(SocialType.Facebook);
        
        stage.setScene( testLogin());
        stage.show();
    }

    private Scene testLogin () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        box.getChildren().add(makeButton(field));
        return new Scene(box, 500, 500);
    }

    private Node makeButton (TextField field) {
        Button button = new Button("Post");
        button.setOnMouseClicked(e -> post(field));
        
        return button;
    }

    private void post (TextField field) {
        myUser = mySocial.getActiveUser();
        if (myUser == null) {
            System.out.println("Login first");
            return;
        }
        myUser.getProfiles().getActiveProfile().customPost(field.getText());
    }

    public static void main (String[] args) {
        launch(args);
    }

}
