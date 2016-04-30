package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import splash.LocaleManager;

public class PathMoverSFV extends SubFormView implements IPathMoverSFV {

    private static String SPEED_LABEL = "PathSpeedLabel";
    private double spacing = 20;
    private ResourceBundle myLabels = ResourceBundle.getBundle("languages/labels", LocaleManager
                                                             .getInstance().getCurrentLocaleProperty().get());
    private NumberEntryView mySpeedInputView;
    
    public PathMoverSFV() {
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
        HBox box = getMyUIFactory().makeHBox(spacing, Pos.CENTER, mySpeedInputView.draw());        
        return getMyUIFactory().addStyling(box, "Mover");
    }

    @Override
    protected void initView () {
        mySpeedInputView = new NumberEntryView(myLabels.getString(SPEED_LABEL), 100, 30, AuthoringView.DEFAULT_ENTRYVIEW);
    }

}
