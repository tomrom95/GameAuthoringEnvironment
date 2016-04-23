package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.AttributeDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.DraggableAddCell;
import gameauthoring.util.DraggableRemoveCell;
import gameauthoring.util.DraggableRemoveCellImage;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Implementation of ISelectAttributeSFV with MultiChoiceEntryView display
 * 
 * @author Joe Lilien
 *
 */
public class SelectAttributeSFV extends SubFormView implements ISelectAttributeSFV {

    private String myAttributesKey = "Available Attributes: ";
    private String mySelectedKey = "Selected Attributes: ";
    private MultiChoiceEntryView<AttributeDefinition> myAttributeSelector;
    private HBox myContainer;
    private UIFactory myUIFactory = new UIFactory();
    private MultiChoiceEntryView<AttributeDefinition> mySelectedView;

    public SelectAttributeSFV (IDefinitionCollection<AttributeDefinition> attributes) {
        myAttributeSelector =
                new MultiChoiceEntryView<AttributeDefinition>(myAttributesKey,
                                                              attributes.getItems(), 150, 200,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
        mySelectedView =
                new MultiChoiceEntryView<AttributeDefinition>(mySelectedKey,
                                                              FXCollections.observableArrayList(),
                                                              300, 200,
                                                              AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    public List<AttributeDefinition> getSelectedAttributes () {
        return mySelectedView.getListView().getItems();
    }

    @Override
    protected void initView () {
        mySelectedView.getListView().setPlaceholder(new Label("Drag Desired Attributes Here"));
        mySelectedView.getListView().setOrientation(Orientation.HORIZONTAL);
        mySelectedView.getListView()
                .setCellFactory(c -> new DraggableRemoveCellImage<AttributeDefinition>(myAttributeSelector
                        .getListView()));
        myAttributeSelector.getListView()
                .setCellFactory(c -> new DraggableAddCell<AttributeDefinition>(mySelectedView
                        .getListView()));
        myContainer = myUIFactory.makeHBox(20, myAttributeSelector.draw(), mySelectedView.draw());
    }

}
