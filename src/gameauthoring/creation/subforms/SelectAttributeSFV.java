package gameauthoring.creation.subforms;

import java.util.List;
import java.util.ResourceBundle;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.DraggableAddCell;
import gameauthoring.util.DraggableRemoveCellImage;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import splash.LocaleManager;


/**
 * Implementation of ISelectAttributeSFV with MultiChoiceEntryView display
 *
 * @author Joe Lilien
 *
 */
public class SelectAttributeSFV extends SubFormView implements ISelectAttributeSFV {

    private static final String MY_TITLE_KEY = "SelectAttribute";
    private ResourceBundle myLabel;
    private String myAttributesKey;
    private String mySelectedKey;
    private MultiChoiceEntryView<AttributeDefinition> myAttributeSelector;
    private MultiChoiceEntryView<AttributeDefinition> mySelectedView;
    private HBox myContainer;

    public SelectAttributeSFV (IDefinitionCollection<AttributeDefinition> attributes) {
        setMyTitle(MY_TITLE_KEY);
        setResoureBunldeAndKey();
        myAttributeSelector =
                new MultiChoiceEntryView<AttributeDefinition>(myAttributesKey,
                                                              attributes.getItems(), 170, 220,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
        mySelectedView =
                new MultiChoiceEntryView<AttributeDefinition>(mySelectedKey,
                                                              FXCollections.observableArrayList(),
                                                              300, 220,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private void setResoureBunldeAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myAttributesKey = myLabel.getString("AttributesKey");
        mySelectedKey = myLabel.getString("SelectedAttributesKey");
    }

    @Override
    public Node draw () {
        return defaultDisplayWithNode(myContainer);
    }

    @Override
    public List<AttributeDefinition> getSelectedAttributes () {
        return mySelectedView.getListView().getItems();
    }

    @Override
    public void setSelectedAttributes (List<AttributeDefinition> items) {
        mySelectedView.getListView().setItems(FXCollections.observableArrayList(items));
    }

    @Override
    protected void initView () {
        mySelectedView.getListView()
                .setPlaceholder(new Label(myLabel.getString("DragAttributeLabel")));
        mySelectedView.getListView().setOrientation(Orientation.HORIZONTAL);
        mySelectedView.getListView()
                .setCellFactory(c -> new DraggableRemoveCellImage<AttributeDefinition>(myAttributeSelector
                        .getListView()));
        myAttributeSelector.getListView()
                .setCellFactory(c -> new DraggableAddCell<AttributeDefinition>(mySelectedView
                        .getListView()));
        myContainer =
                getMyUIFactory().makeHBox(20, Pos.CENTER, myAttributeSelector.draw(),
                                          mySelectedView.draw());
    }

}
