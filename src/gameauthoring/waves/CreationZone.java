package gameauthoring.waves;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.CheckEntryView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.Glyph;
import gameauthoring.util.UIFactory;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


public class CreationZone implements Glyph {

    private HBox myPane = new HBox();
    private UIFactory myFactory = new BasicUIFactory();
    private Button myActionButton;
    private Button mySaveButton;
    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private String myInfiniteKey = myLang.getString("InfiniteWaveKey");
    private CheckEntryView myInfiniteSelect;

    public CreationZone () {
        init();
    }

    private void init () {

        myActionButton = myFactory.createStyledButton(myLang.getString("AddWave"), "CustomButton");
        mySaveButton = myFactory.createStyledButton(myLang.getString("SaveWave"), "CustomSave");
        mySaveButton.setVisible(false);
        myInfiniteSelect = new CheckEntryView(myInfiniteKey, AuthoringView.DEFAULT_ENTRYVIEW);
        myPane.getChildren().add(myActionButton);
        myPane.getChildren().add(mySaveButton);
        myPane.getChildren().add(myInfiniteSelect.draw());

    }

    @Override
    public Node draw () {
        return myPane;
    }

    public void setButtonAction (EventHandler<MouseEvent> event) {
        myActionButton.setOnMouseClicked(event);
    }

    public void setSaveButtonAction (EventHandler<MouseEvent> event) {
        mySaveButton.setOnMouseClicked(event);
    }

    public void enterEdit () {
        myActionButton.setText("Exit edit mode");
        mySaveButton.setVisible(true);
    }

    public void exitEdit () {
        setInfiniteCheckBox(false);
        myActionButton.setText(myLang.getString("AddWave"));
        mySaveButton.setVisible(false);
    }

    public BooleanProperty isInfiniteProperty () {
        return myInfiniteSelect.isCheckedProperty();

    }

    public void setInfiniteCheckBox (boolean b) {
        myInfiniteSelect.setSelected(b);
    }

}
