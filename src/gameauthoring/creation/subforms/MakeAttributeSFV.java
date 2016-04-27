package gameauthoring.creation.subforms;

import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;


/**
 * Implementation of IMakeAttributeSFV with GridPane Arrangement and NumberEntryView display
 * 
 * @author Jin An
 * @author Joe Lilien
 *
 */
public class MakeAttributeSFV extends SubFormView implements IMakeAttributeSFV {

    private String myStartingValueLabel = "Starting Value: ";
    private NumberEntryView myStartingValue;

    public MakeAttributeSFV () {
        myStartingValue = new NumberEntryView(myStartingValueLabel, 100, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
    }

    @Override
    public double getStartingValue() {
        return myStartingValue.getData();
    }
    
    @Override
    public void populateWithData(double value) {
        myStartingValue.setData(value);
    }
    
    @Override
    public Node draw () {
        return myStartingValue.draw();
    }
}
