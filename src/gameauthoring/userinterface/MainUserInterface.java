package gameauthoring.userinterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.ResourceBundle;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * This is the main user inteface class which contains two buttons "Create game" and
 * "Load existing game". 
 * 
 * @TODO: ResourceBundle for unprotected string
 * @TODO: Load Game method
 * @author Jin An
 *
 */
public class MainUserInterface {

    private static final String TITLE = "Welcome to GitDepends Game Salad";
    private static final String RESOURCES_PATH = "resource/MainUserInterface";
    private static final int SPACING = 20;
    private ResourceBundle myResources;
    private Stage myStage;
    private BorderPane myLayout;

    public MainUserInterface () {
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
    }

    public void init (Stage s) {
        myStage = s;
        myLayout = new BorderPane();
        myLayout.setCenter(createButtons());
        Scene scene = new Scene(myLayout, AuthoringView.WIDTH, AuthoringView.HEIGHT);
        s.setTitle(TITLE);
        s.setScene(scene);
        s.show();
    }

    public Node createButtons () {
        VBox root = new VBox(SPACING);
        root.setAlignment(Pos.CENTER);
        Enumeration<String> keys = myResources.getKeys();
        while (keys.hasMoreElements()) {
            root.getChildren().add(createButton(keys.nextElement()));
        }
        return root;
    }

    private Button createButton (String btnName) {
        Button newButton = new Button(myResources.getString(btnName));

        try {
            Method method = this.getClass().getMethod(btnName);
            newButton.setOnAction(event -> callMethod(method));
        }
        catch (NoSuchMethodException | SecurityException e) {
            System.out.println("error");
        }

        return newButton;

    }

    private void callMethod (Method method) {
        try {
            method.invoke(this);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
           e.printStackTrace();
            return;
        }
    }

    public void createGame () {
        AuthoringView aView = new AuthoringView();
        aView.init(myStage);
    }

    public void loadGame () {

    }
}
