package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import splash.LocaleManager;
import util.StringParser;

public class PathMoverSFV extends SubFormView implements IPathMoverSFV {
    
    private GridPane myPane = new GridPane();

    private static String SPEED_LABEL = "PathSpeedLabel";
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
        return myPane;
    }

    @Override
    protected void initView () {
        double width = getParser().parseDouble(getMyNumbers().getString("Width"));
        double height = getParser().parseDouble(getMyNumbers().getString("Height"));
        mySpeedInputView = new NumberEntryView(myLabels.getString(SPEED_LABEL), width, height, AuthoringView.DEFAULT_ENTRYVIEW);
        myPane.add(mySpeedInputView.draw(), 0, 0);
        myPane.getStyleClass().add("mover");

    }

}
