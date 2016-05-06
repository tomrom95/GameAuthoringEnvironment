package gameauthoring.creation.subforms;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import splash.LocaleManager;


public class AISpriteSFV extends SubFormView implements IAISpriteSFV {

    private static final int SPACING = 5;
    private String myAILabel;
    private String myObstructableLabel;
    private String myGoalLabel;

    private CheckEntryView myObstructabilityCheckBox;
    private CheckEntryView myGoalCheckBox;

    private HBox myContainer;
    private ResourceBundle myLabels = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());
    private TitledPane myTitledPane;

    public AISpriteSFV () {

        myAILabel = myLabels.getString("AILabel");
        myObstructableLabel = myLabels.getString("AIObstructionLabel");
        myGoalLabel = myLabels.getString("AIGoalLabel");

        initView();
    }

    @Override
    public Node draw () {
        getMyUIFactory().addStyling(myTitledPane, getStyleClass());
        return myTitledPane;
    }

    @Override
    protected void initView () {
        myObstructabilityCheckBox =
                new CheckEntryView(myObstructableLabel, AuthoringView.DEFAULT_ENTRYVIEW);
        myGoalCheckBox = new CheckEntryView(myGoalLabel, AuthoringView.DEFAULT_ENTRYVIEW);
        myObstructabilityCheckBox.setSelected(true);
        myGoalCheckBox.setSelected(false);
        myGoalCheckBox.isCheckedProperty()
                .addListener( (obs, wasSelected, isNowSelected) -> myObstructabilityCheckBox
                        .setSelected(!isNowSelected));
        myObstructabilityCheckBox.isCheckedProperty()
                .addListener( (obs, wasSelected, isNowSelected) -> myGoalCheckBox
                        .setSelected(!isNowSelected));

        myContainer = getMyUIFactory().makeHBox(SPACING, Pos.CENTER, drawFields());
        myTitledPane = getMyUIFactory().makeCheckBoxTitledPane(myAILabel, myContainer, false);
        setIsAI(false);

    }

    private Node drawFields () {
        VBox box = new VBox(SPACING);
        box.getChildren().addAll(myObstructabilityCheckBox.draw(),
                                 myGoalCheckBox.draw());
        return box;
    }

    @Override
    public boolean getIsObstructable () {
        return myObstructabilityCheckBox.isCheckedProperty().get();
    }

    private void setIsObstructable () {
        myObstructabilityCheckBox.isCheckedProperty().set(true);
    }

    @Override
    public boolean getIsGoal () {
        return myGoalCheckBox.isCheckedProperty().get();
    }

    private void setIsGoal () {
        myObstructabilityCheckBox.isCheckedProperty().set(false);
    }

    private void setIsNone () {
        myObstructabilityCheckBox.isCheckedProperty().set(true);
        setIsAI(false);
    }

    private void setIsAI (boolean value) {
        myTitledPane.setExpanded(value);
    }

    @Override
    public boolean getIsAI () {
        return myTitledPane.isExpanded();
    }

    @Override
    public void populateWithData (boolean isObstructable, boolean isGoal, boolean isAI) {
        setIsAI(isAI);
        if (isObstructable) {
            setIsObstructable();
        }
        else if (isGoal) {
            setIsGoal();
        }

    }
}
