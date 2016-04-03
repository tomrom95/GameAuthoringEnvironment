package gameauthoring.levels;

import java.io.File;
import engine.IGame;
import gameauthoring.Glyph;
import gameauthoring.ListCellView;
import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;

public class SceneCreator implements Glyph{
    private final static String DEFAULT_BACKGROUND = "images/blank.jpg";
    
    private IGame gameModel;
    private boolean inPathCreator = false;
    private Path myPath;
    
    public SceneCreator (IGame model){
        gameModel = model;
    }

    @Override
    public Node draw () {
        HBox container = new HBox(10);
        container.getChildren().add(createLevelView());
        container.getChildren().add(createSpriteSelection());
        return container;
    }

    @Override
    public void update () {
        
    }
    
    private Node createSpriteSelection () {
        Accordion selector = new Accordion();
        ListView<Node> testList = new ListView<Node>();
        for(int i = 0; i < 5; i ++){
            ListCellView sprite = new SpriteCellView(null);
            testList.getItems().add(sprite.draw());
        }
        TitledPane friendlies = new TitledPane("Enemies", testList);
        selector.getPanes().add(friendlies);
        return selector;
    }

    private Node createLevelView () {
        Pane container = new Pane();
        
        setBackground(container, DEFAULT_BACKGROUND);
        container.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> uploadNewBackground(e, container));
        container.setOnMouseClicked(e-> handleMouseClick(e, container));
        container.setOnKeyPressed(e -> handleKeyPress(e, container));
        return container;
    }

    private void handleKeyPress (KeyEvent e, Pane container) {
        if(e.getCode() == KeyCode.ESCAPE){
            inPathCreator = false;
            container.setOnMouseMoved(null);
            int last = myPath.getElements().size() - 1;
            if(myPath.getElements().get(last) instanceof LineTo){
                myPath.getElements().remove(last);
            }
        }
    }

    private void handleMouseClick (MouseEvent e, Pane container) {
        if (e.getButton() == MouseButton.SECONDARY){
            uploadNewBackground(e, container);
        } else if (inPathCreator){
            addToPath(e, container);
        } else {
            createPath(e, container);
        }
    }

    private void createPath (MouseEvent e, Pane container) {
        if(inPathCreator) return;
        inPathCreator = true;
        container.requestFocus();
        myPath = new Path();
        container.getChildren().add(myPath);
        addToPath(e, container);
    }

    private void followMouse (MouseEvent event, LineTo line) {
        line.setX(event.getX());
        line.setY(event.getY());
    }

    private void addToPath (MouseEvent event, Pane container) {
        if(!inPathCreator) return;
        myPath.getElements().add(new MoveTo(event.getX(), event.getY()));
        LineTo line = new LineTo(event.getX(), event.getY());
        myPath.getElements().add(line);
        container.setOnMouseMoved(e -> followMouse(e, line));
    }

    private void uploadNewBackground (MouseEvent e, Pane container) {
        if(e.getButton() != MouseButton.SECONDARY){
                return;
        }
        File newImage = (new FileChooser()).showOpenDialog(null);
        if (newImage == null) return;
        setBackground(container, newImage.toURI().toString());
    }
    
    public void setBackground (Region container, String imagePath){
        Image img = new Image(imagePath, 0, 300, true, true);
        BackgroundImage background = new BackgroundImage(
                 img,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT);
        container.setBackground(new Background(background));
        container.setMinWidth(img.getWidth());
        container.setMinHeight(img.getHeight());
    }
    
    

}
