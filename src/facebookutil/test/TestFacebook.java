package facebookutil.test;

import facebookutil.JavaSocial;
import facebookutil.SocialType;
import facebookutil.applications.App;
import facebookutil.scores.ScoreOrder;
import facebookutil.user.Email;
import facebookutil.user.IUser;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        mySocial.loginUser(SocialType.FACEBOOK);
        addScores(mySocial);
        stage.setScene(testLogin());
        stage.show();
    }

    private void addScores (JavaSocial social) {
        IUser user1 = social.createNewUser(new Email("fake", "fake.com"));
        IUser user2 = social.createNewUser(new Email("other", "other.com"));
        IUser user3 = social.createNewUser(new Email("last", "last.com"));
        social.getHighScoreBoard().addNewScore("game1", user1, 50);
        social.getHighScoreBoard().addNewScore("game1", user2, 1000);
        social.getHighScoreBoard().addNewScore("game1", user3, 3000);
    }

    private Scene testLogin () {
        VBox box = new VBox(5);
        createFields (box);
        return new Scene(box, 500, 500);
    }

    private void createFields (VBox box) {
        box.getChildren().add(makePost());
        box.getChildren().add(makeNotify());
        box.getChildren().add(makeAddHighScore());
        box.getChildren().add(makePostHighScoreBoard());
        box.getChildren().add(makeMyHighScorePost());
        box.getChildren().add(makeChallenge());
        box.getChildren().add(makeLogout());
    }

    private Node makeAddHighScore () {
        HBox box = new HBox(5);
        TextField field = new TextField("Score");
        TextField gameName = new TextField("game name");
        box.getChildren().addAll(gameName, field);
        Button button = new Button("Add high score");
        button.setOnMouseClicked(e -> {
            int score = Integer.valueOf(field.getText());
            mySocial.getHighScoreBoard().addNewScore(gameName.getText(), mySocial.getActiveUser(), score);
        });
        box.getChildren().add(button);
        return box;
    }

    private Node makeChallenge () {
        HBox box = new HBox(5);
        TextField field = new TextField("Challenge text");
        box.getChildren().add(field);
        Button button = new Button("Challenge");
        button.setOnMouseClicked(e -> {
            myUser = mySocial.getActiveUser();
            myUser.getProfiles().getActiveProfile().challenge(myUser, myUser, field.getText());
        });
        box.getChildren().add(button);
        return box;
    }

    private Node makePostHighScoreBoard () {
        HBox box = new HBox(5);
        TextField field = new TextField("Game name");
        ComboBox<ScoreOrder> combo = new ComboBox<>();
        for (ScoreOrder s: ScoreOrder.values()) {
            combo.getItems().add(s);
        }
        box.getChildren().addAll(field, combo);
        Button button = new Button("Post global score board");
        button.setOnMouseClicked(e -> {
            myUser = mySocial.getActiveUser();
            myUser.getProfiles().getActiveProfile().highScoreBoardPost(mySocial.getHighScoreBoard(),
                                                                       field.getText(),
                                                                       combo.getValue());
        });
        box.getChildren().add(button);
        return box;
    }
    
    private Node makeMyHighScorePost () {
        HBox box = new HBox(5);
        TextField field = new TextField("Game name");
        ComboBox<ScoreOrder> combo = new ComboBox<>();
        for (ScoreOrder s: ScoreOrder.values()) {
            combo.getItems().add(s);
        }
        box.getChildren().addAll(field, combo);
        Button button = new Button("Post about my scores");
        button.setOnMouseClicked(e -> {
            myUser = mySocial.getActiveUser();
            myUser.getProfiles().getActiveProfile().highScorePost(mySocial.getHighScoreBoard(),
                                                                  field.getText(),
                                                                  myUser,
                                                                  combo.getValue());
        });
        box.getChildren().add(button);
        return box;
    }

    private Node makeLogout () {
        Button button = new Button("Logout");
        button.setOnMouseClicked(e -> mySocial.saveState());
        return button;
    }

    private Node makePost () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Custom Post");
        button.setOnMouseClicked(e -> post(field));
        box.getChildren().add(button);
        return box;
    }
    
    private Node makeNotify () {
        HBox box = new HBox(5);
        TextField field = new TextField();
        box.getChildren().add(field);
        Button button = new Button("Notify all users");
        button.setOnMouseClicked(e -> notify(field));
        box.getChildren().add(button);
        return box;
    }

    private void notify (TextField field) {
        myApp = mySocial.getApplications().getAppByType(SocialType.FACEBOOK);
        if (myApp == null) {
            System.out.println("Something's wrong");
            return;
        }
        myApp.notifyUsers(mySocial.getUsers(), field.getText());
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
