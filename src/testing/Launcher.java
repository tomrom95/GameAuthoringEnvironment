package testing;

import java.util.ArrayList;
import java.util.List;
import engine.ControlKeys;
import engine.GraphicModule;
import engine.IOInterpeter;
import engine.PathFollowMover;
import engine.Sprite;
import engine.UserControlledMover;
import gameplayer.GamePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Coordinate;
import util.Key;
import util.RGBColor;
import gameplayer.Renderer;
import graphics.Block;
import graphics.TextGraphic;
import util.TimeDuration;


public class Launcher extends Application {

    private IOInterpeter io;
    private Sprite sprite;
    private Pane myPane;
    private Renderer r;
    
    public static void main (String[] args) {
        launch(args);
    }
    
    private void initializeTimeline () {
        Timeline timeline = new Timeline();
        Duration frameDuration = Duration.seconds(1.0d / 60);
        KeyFrame repeatedFrame = new KeyFrame(frameDuration, e -> step(frameDuration));
        timeline.getKeyFrames().add(repeatedFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void step (Duration frameDuration) {
        sprite.update(new TimeDuration(10));
        io.deQueueKeyEvents().forEach(e-> sprite.registerKeyEvent(e));
        r.render();
        r.draw(sprite);
      
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        
        Stage myStage = new Stage();
        myPane = new Pane();
        myPane.setStyle("-fx-background-color: blue;");
        Scene myScene = new Scene(myPane);
        myPane.setPrefSize(1000, 400);
        myStage.setScene(myScene);
        myStage.show();
        myPane.setOnKeyPressed(e-> System.out.println("Pane"));
        io = new IOInterpeter (myPane);
        myPane.requestFocus();
        ControlKeys keys = new ControlKeys(new Key("Up"), new Key("Left"), new Key("Right"), new Key("Down"));
        ObjectProperty<Coordinate> c = new SimpleObjectProperty<>(new Coordinate(0,0));
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(100,100));
        list.add(new Coordinate(20, 20));
        list.add(new Coordinate(200, 2));
        list.add(new Coordinate(700, 700));
        sprite = new Sprite(c);
        sprite.getMovementStrategyProperty().set(new PathFollowMover(c, .10, list));
        initializeTimeline();
        r = new Renderer(null, myPane);
        Block graphic = new Block(40, 40, RGBColor.BLACK);
        sprite.getDrawer().set(new GraphicModule(graphic));
        myStage.show();
        r.draw(sprite);
    }
    

}
