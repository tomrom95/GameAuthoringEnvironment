package gameplayer.facebook;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import engine.IGame;
import facebookutil.JavaSocial;
import facebookutil.user.IUser;
import gameauthoring.util.ErrorMessage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
 *
 * @author Tommy
 *
 */
public class FacebookMenu {
    private static final Dimension SIZE = new Dimension(500, 500);
    private static final int SPACING = 15;
    private static final String PATH = "defaults/facebook";
    private static final String VIEW_SPLIT = ",";
    private static final String INFO_STRING = "Info";
    private static final String BUTTON_STRING = "Button";

    private Stage myStage;
    private FacebookController myController;
    private ResourceBundle myResources = ResourceBundle.getBundle(PATH);

    public FacebookMenu (IGame game) {
        myStage = new Stage();
        myController = new FacebookController(game);
        Scene scene = initScene();
        myStage.setScene(scene);
    }

    public void popUp () {
        myStage.show();
    }

    /**
     * Initializes the box with proper spacing
     *
     * @return
     */
    private Scene initScene () {
        VBox box = new VBox(SPACING);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        addAllViews(box);
        return new Scene(box, SIZE.getWidth(), SIZE.getHeight());
    }

    private void addAllViews (VBox box) {
        List<String> viewStrings = Arrays.asList(myResources.getString("views").split(VIEW_SPLIT));
        viewStrings.stream()
                .map(v -> getNode(v))
                .forEach(v -> box.getChildren().add(v));
    }

    private Node getNode (String viewString) {
        try {
            Method m = this.getClass().getMethod(viewString, String.class);
            Node node = (Node) m.invoke(this, viewString);
            return makeTitleBox(node, viewString);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            return new HBox(SPACING);
        }
    }

    /**
     * Makes a titled node using a vbox
     *
     * @param node you want titled
     * @param info - title string
     * @return
     */
    private Node makeTitleBox (Node node, String viewString) {
        VBox box = new VBox(SPACING);
        String info = myResources.getString(viewString + INFO_STRING);
        box.getChildren().add(new Text(info));
        box.getChildren().add(node);
        return box;
    }

    /**
     * Helper to make a button
     *
     * @param string
     * @param event
     * @return
     */
    private Button makeButton (String string, EventHandler<? super MouseEvent> event) {
        String buttonString = myResources.getString(string + BUTTON_STRING);
        Button button = new Button(buttonString);
        button.setOnMouseClicked(event);
        return button;
    }

    /**
     * Creates the post high score button
     *
     * @return
     */
    public Node highScorePost (String viewString) {
        Button button = makeButton(viewString,
                                   e -> postScoreOrError());
        return button;
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
     * Creates the custom post field and button
     *
     * @return
     */
    public Node customPost (String viewString) {
        HBox box = new HBox(SPACING);
        TextField message = new TextField();
        Button button =
                makeButton(viewString,
                           e -> myController.postCustom(message.getText()));
        box.getChildren().addAll(message, button);
        return box;
    }

    public Node highScoreBoardPost (String viewString) {
        Button button = makeButton(viewString,
                                   e -> myController.postHighScoreBoard());
        return button;
    }

    public Node challengeUser (String viewString) {
        HBox box = new HBox(SPACING);
        TextField message = new TextField();
        ComboBox<IUser> combo = new ComboBox<IUser>();
        JavaSocial.getInstance().getUsers()
                .forEach(u -> combo.getItems().add(u));
        Button button =
                makeButton(viewString,
                           e -> myController.challengeUser(combo.getValue(), message.getText()));
        box.getChildren().addAll(message, combo, button);
        return box;
    }

}
