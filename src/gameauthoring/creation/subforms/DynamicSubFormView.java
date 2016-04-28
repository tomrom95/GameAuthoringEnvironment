package gameauthoring.creation.subforms;

import java.util.List;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * This in an abstract class for an SFV that needs to dynamically change its
 * sub-subview based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public class DynamicSubFormView extends ClickAndFillView {

    /**
     * Constructor
     * 
     * TODO: options should be retreived directly either from title of either sfc or sfv
     * 
     * @param views The list of sub-subformviews to dynamically switch between
     * @param action The method to call when user selects a different sub-subview
     * @param options A list of strings containing the titles of each sub-subview
     */
    public DynamicSubFormView (List<String> options) {
        super(options);
        initView();

    }

    @Override
    protected void addOrSetSFV (ISubFormView sfv) {
        getMyPaneContent().getChildren().clear();
        getMyPaneContent().getChildren().add(sfv.draw());
    }

}
