package gameauthoring.creation.subforms.movement;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.scene.layout.HBox;

/**
 * UNUSED WITH NEW DYNAMIC FORM STRUCTURE
 * TODO: remove this class
 */
/**
 * This serves to display movement subform view 
 * 
 * @author Jin An, Jeremy Schreck
 *
 */
public class MovementSubFormView extends DynamicSubFormView {

    public MovementSubFormView (List<String> options) {
        super(options);
        // TODO Auto-generated constructor stub
    }
    private ResourceBundle myProperties = ResourceBundle.getBundle("defaults/click_and_fill_images");
    private String myMoveTypeKey = "Movement Type: ";

    /**
     * Constructor 
     * 
     * @param views The sub-subformviews representing different types of movement
     * @param action The method to call when user selects a different movement type
     * @param options  The titles of the different movement options
     */
//    public MovementSubFormView (List<ISubFormView> views,
//                                List<String> options) {
//        super(views, options);
//    }
//
//    @Override
//    protected String getSelectionKey () {
//        return myMoveTypeKey;
//    }
//
//    @Override
//    protected void initAndAddButtons (HBox buttonHolder) {
//        
//    }
}
