package gameauthoring;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Handles selection between differnet games
 * 
 * @author Jin An
 *
 */
public class GameTabViewer implements ITabViewer {

    private Tab myGameTab;
    private BorderPane myLayout;

    public GameTabViewer (Tab gameTab) {
        myGameTab = gameTab;
    }

    public Tab getTab () {
        return myGameTab;
    }

    @Override
    public Node draw () {
        myLayout = new BorderPane();
        myLayout.setPrefSize(1200, 800);
        myLayout.setCenter(createForms());
        myGameTab.setContent(myLayout);
        return myLayout;
    }

    private Node createForms(){
        HBox box = new HBox(800);
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.getChildren().add(createGameInfoForm());
        return box;
    }
    
    private Node createGameInfoForm(){
        GridPane form = new GridPane();
        Button loadImage = makeButton("Load", e -> fileLoader());
        form.setMinSize(200, 200);
    /*    form.add(new TextFormData("Name of the Game: ").draw(), 0, 1);
        form.add(new TextFormData("Author: ").draw(), 0, 2);
        form.add(new TextFormData("Splash Sreen: ").draw(), 0, 3);*/
        form.add(loadImage, 1, 3);
        return form;
    }
    
    private Button makeButton(String buttonName, EventHandler<ActionEvent> e){
        Button b = new Button(buttonName);
        b.setOnAction(e);
        return b;
    }
    
    private void fileLoader() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your Splash Screen");
        File file = fileChooser.showOpenDialog(new Stage());
        //TODO: Fill out the path route on the textbox.
    }
    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

}
