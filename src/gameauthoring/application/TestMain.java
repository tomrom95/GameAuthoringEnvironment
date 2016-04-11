package gameauthoring.application;

import engine.ICondition;
import engine.OnClickCondition;
import gameauthoring.AuthoringView;
import gameauthoring.ConditionView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;


public class TestMain extends Application {

    @Override
    public void start (Stage s) throws Exception {
        Stage stage = new Stage();
        Group root = new Group();
        ConditionView myConditionView =
                new ConditionView(new Tab(), getTestConditions(), getOptions());
        root.getChildren().add(myConditionView.draw());
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }

    public static void main (String[] args) {
        launch(args);
    }

    private ObservableList<String> getOptions () {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Ryan");
        options.add("Tayla");
        options.add("Maydew");
        return options;
    }

    private ObservableList<ICondition> getTestConditions () {
        ObservableList<ICondition> conditions = FXCollections.observableArrayList();
        ICondition c = new OnClickCondition(null, null, null, null);
        conditions.add(c);
        return conditions;
    }

}
