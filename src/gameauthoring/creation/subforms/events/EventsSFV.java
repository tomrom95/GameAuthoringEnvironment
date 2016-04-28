package gameauthoring.creation.subforms.events;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import splash.LocaleManager;
import gameauthoring.creation.subforms.DynamicSubFormView;
import gameauthoring.creation.subforms.ISubFormView;


/**
 * CLASS NOT USED UNDER CURRENT STRUCTURE
 * TODO: remove this class
 * 
 * @author Owner
 *
 */
public class EventsSFV extends DynamicSubFormView {

    private ResourceBundle myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
            .getInstance().getCurrentLocaleProperty().get());

    public EventsSFV (List<ISubFormView> subforms,
                      Consumer<Integer> changeSelectionAction,
                      List<String> options) {
        super(options);
    }

    protected String getSelectionKey () {
        return myLabel.getString("SelectionKey");
    }

}
