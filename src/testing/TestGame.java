package testing;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.TimeDuration;

public class TestGame extends Application {

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
      
    }
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        
    }

}
