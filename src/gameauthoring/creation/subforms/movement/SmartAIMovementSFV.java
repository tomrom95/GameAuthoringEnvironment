package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import splash.LocaleManager;

public class SmartAIMovementSFV extends SubFormView implements ISmartAIMovementSFV {

    private static String SPEED_LABEL = "AISpeedLabel";
    private ResourceBundle myLabels = ResourceBundle.getBundle("languages/labels", LocaleManager
                                                             .getInstance().getCurrentLocaleProperty().get());
    private NumberEntryView mySpeedInputView;
    
    public SmartAIMovementSFV() {
        initView();
    }
    @Override
    public double getMySpeed () {
        return mySpeedInputView.getData();
    }

    @Override
    public void populateWithData (double speed) {
        mySpeedInputView.setData(speed);
    }

    @Override
    public Node draw () {
        double spacing = getParser().parseDouble(getMyNumbers().getString("HBoxSpacing"));
        HBox box = getMyUIFactory().makeHBox(spacing, Pos.CENTER, mySpeedInputView.draw());        
        return getMyUIFactory().addStyling(box, "Mover");

    }

    @Override
    protected void initView () {
        double width = getParser().parseDouble(getMyNumbers().getString("Width"));
        double height = getParser().parseDouble(getMyNumbers().getString("Height"));
        mySpeedInputView = new NumberEntryView(myLabels.getString(SPEED_LABEL), width, height, AuthoringView.DEFAULT_ENTRYVIEW);
    }

}
