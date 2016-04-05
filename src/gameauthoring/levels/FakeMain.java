/**
 * @author Austin Wu
 *         The Main file that starts the simulation
 */

package gameauthoring.levels;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FakeMain extends Application {

    /**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start (Stage stage) {
        LevelEditorView view = new LevelEditorView(null);
        Group root = new Group(view.draw());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}

