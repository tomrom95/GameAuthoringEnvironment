package gameplayer.facebook;

import java.awt.Dimension;
import java.util.ResourceBundle;
import engine.IGame;
import gameauthoring.util.ErrorMessage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Pops up menu so that you can do things on facebook like post or
 * send high score. If the user isn't logged into facebook, doesn't
 * do anything
 * @author Tommy
 *
 */
public class FacebookMenu {
    private static final Dimension SIZE = new Dimension(500, 200);
    private static final int SPACING = 15;
    private static final String PATH = "defaults/facebook";

    private Stage myStage;
    private FacebookController myController;
    private ResourceBundle myResources = ResourceBundle.getBundle(PATH);

    public FacebookMenu (IGame game) {
        myStage = new Stage();
        myController = new FacebookController(game);
        Scene scene = initScene();
        myStage.setScene(scene);
    }

    /**
     * Initializes the box with proper spacing
     * @return
     */
    private Scene initScene () {
        VBox box = new VBox(SPACING);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        box.getChildren().add(createCustomPost());
        box.getChildren().add(createHighScorePost());
        return new Scene(box, SIZE.getWidth(), SIZE.getHeight());
    }

    /**
     * Makes a titled node using a vbox
     * @param node you want titled
     * @param info - title string
     * @return
     */
    private Node makeTitleBox (Node node, String info) {
        VBox box = new VBox(SPACING);
        box.getChildren().add(new Text(info));
        box.getChildren().add(node);
        return box;
    }

    /**
     * Creates the post high score button
     * @return
     */
    private Node createHighScorePost () {
        Button button = makeButton(myResources.getString("highscore"),
                                   e -> postScoreOrError());
        return makeTitleBox(button, myResources.getString("highscoreinfo"));
    }

    /**
     * Catches if the game has a high score attribute
     */
    private void postScoreOrError () {
        if (!myController.postHighScore()) {
            new ErrorMessage(myResources.getString("noscore")).showError();
        }
    }

    /**
     * Helper to make a button
     * @param string
     * @param event
     * @return
     */
    private Button makeButton (String string, EventHandler<? super MouseEvent> event) {
        Button button = new Button(string);
        button.setOnMouseClicked(event);
        return button;
    }

    /**
     * Creates the custom post field and button
     * @return
     */
    private Node createCustomPost () {
        HBox box = new HBox(SPACING);
        TextField message = new TextField();
        Button button =
                makeButton(myResources.getString("custom"),
                           e -> myController.postCustom(message.getText()));
        box.getChildren().addAll(message, button);
        return makeTitleBox(box, myResources.getString("custominfo"));
    }

    public void popUp () {
        myStage.show();
    }

}
