package testing;

import engine.GraphicModule;
import engine.IOInterpeter;
import engine.Sprite;
import engine.UserControlledMover;
import gameplayer.GamePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Coordinate;
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
        Duration frameDuration = Duration.seconds(1.0d / 1);
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
        System.out.println(myPane.getChildren());
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        
        Stage myStage = new Stage();
        myPane = new Pane();
        myPane.resize(300, 300);
        Scene myScene = new Scene(myPane);
        myStage.setScene(myScene);
        myStage.show();
        myPane.setOnKeyPressed(e-> System.out.println("Pane"));
        io = new IOInterpeter (myPane);
        myPane.requestFocus();
        sprite = new Sprite();
        sprite.getMovementStrategyProperty().set(new UserControlledMover(new Coordinate(10, 10), 10));
        initializeTimeline();
        r = new Renderer(null, myPane);
        TextGraphic graphic = new TextGraphic("ryan", 40);
        sprite.getDrawer().set(new GraphicModule(graphic));
        myPane.getChildren().add(new Rectangle(10, 10, 10, 10));
        myStage.show();
    }
    

}
