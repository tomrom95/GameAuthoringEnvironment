package gameplayer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class GamePlayerTools {

    private static final String TOOL_PATH = "defaults/ToolBar";
    ResourceBundle myToolButtons = ResourceBundle.getBundle(TOOL_PATH);
    MenuBar myBar = new MenuBar();
    ToolBar myTools = new ToolBar();
    IGameEngine myEngine;

    public GamePlayerTools (IGameEngine engine) {
        init();
    }

    private void init () {
        initMenuBar();
        initToolBar();

    }

    private void initToolBar () {
        Enumeration<String> keys = myToolButtons.getKeys();
        while (keys.hasMoreElements()) {
            String next = keys.nextElement();
            System.out.println(next);
            myTools.getItems().add(createButton(next,
                                                myToolButtons.getString(next)));
        }

    }

    private Node createButton (String key, String url) {
        
        Button button = new Button();
        Method method;
        try {
            method = this.getClass().getMethod(key);
            button.setOnMouseClicked(event -> callMethod(method));

        }
        catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        button.setGraphic(getImage(url));      
        return button;
       
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

    private ImageView getImage (String url) {
        ImageView image = new ImageView(url);
        image.setFitHeight(20);
        image.setFitWidth(20);
        return image;
    }

    private void initMenuBar () {
        Menu controls = new Menu("Controls");
        myBar.getMenus().add(controls);
        MenuItem pause = new MenuItem("Pause");
        MenuItem play = new MenuItem("Play");

        pause.setOnAction(e -> pause());
        play.setOnAction(e -> play());
        controls.getItems().add(play);
        controls.getItems().add(pause);
    }

    public void play () {
        myEngine.play();
    }

    public void pause () {
        myEngine.pause();
    }

    public Node draw () {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(myBar, myTools);
        return vbox;
    }

}
