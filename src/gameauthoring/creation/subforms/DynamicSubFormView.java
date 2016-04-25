package gameauthoring.creation.subforms;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * This in an abstract class for an SFV that needs to dynamically change its
 * sub-subview based on user input
 * 
 * @author Jeremy Schreck
 *
 */
public class DynamicSubFormView extends ClickAndFillView {


    private ResourceBundle myProperties =
            ResourceBundle.getBundle("/defaults/click_and_fill_images");
    private BasicUIFactory myUIFactory = new BasicUIFactory();

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

    @Override
    protected void initAndAddButtons (HBox buttonHolder, List<String> options) {
        for(String s : options){
            Button button = myUIFactory.createImageButton(myUIFactory.makeImageDisplay(myProperties.getString(s), s));
            getMyButtons().add(button);
        }
        buttonHolder.getChildren().addAll(getMyButtons());
    }

}
