package gameauthoring.creation.subforms.movement;

import java.util.List;
import java.util.function.Consumer;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This serves to display movement subform view. It creates a combo box which allows the author
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class MovementSubFormView extends DynamicSubFormView {

    private String myMoveTypeKey = "Movement Type: ";

    public MovementSubFormView (List<ISubFormView> views,
                                Consumer<Integer> action,
                                List<String> options) {
        super(views, action, options);
    }

    @Override
    protected String getSelectionKey () {
        return myMoveTypeKey;
    }
}
