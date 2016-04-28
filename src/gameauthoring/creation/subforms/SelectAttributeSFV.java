package gameauthoring.creation.subforms;

import java.util.List;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.DraggableAddCell;
import gameauthoring.util.DraggableRemoveCell;
import gameauthoring.util.DraggableRemoveCellImage;
import gameauthoring.util.UIFactory;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
    private BasicUIFactory myUIFactory = new BasicUIFactory();
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
    public void setSelectedAttributes (List<AttributeDefinition> items) {
        mySelectedView.getListView().setItems(FXCollections.observableArrayList(items));
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
        myContainer = myUIFactory.makeHBox(20, Pos.CENTER, myAttributeSelector.draw(), mySelectedView.draw());
    }

}
