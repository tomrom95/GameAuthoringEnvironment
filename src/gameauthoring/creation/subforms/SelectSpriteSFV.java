package gameauthoring.creation.subforms;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import java.util.*;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.DraggableAddCell;
import gameauthoring.util.DraggableRemoveCell;
import gameauthoring.util.UIFactory;


/**
 * Implementation of ISelectSpriteSFV using accoridon and MultiChoiceEntryView
 * 
 * @author JoeLilien
 *
 */
public class SelectSpriteSFV extends SubFormView implements ISelectSpriteSFV {
    private Accordion myAccordion;
    private HBox myContainer;
    private List<MultiChoiceEntryView<SpriteDefinition>> myViews;
    private List<DefinitionCollection<SpriteDefinition>> mySprites;
    private MultiChoiceEntryView<SpriteDefinition> mySelected;
    private UIFactory myUIFactory = new UIFactory();
    private String mySelectedLabel = "Sprites in Group: ";

    public SelectSpriteSFV (List<DefinitionCollection<SpriteDefinition>> sprites) {
        myViews = new ArrayList<>();
        mySprites = sprites;
        mySelected =
                new MultiChoiceEntryView<>(mySelectedLabel, FXCollections.observableArrayList(),
                                           200, 400, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
        myAccordion = myUIFactory.makeAccordion(300);
        for (IDefinitionCollection<SpriteDefinition> def : mySprites) {
            MultiChoiceEntryView<SpriteDefinition> myView =
                    new MultiChoiceEntryView<>(def.getTitle(), def.getItems(), 300, 400,
                                               AuthoringView.DEFAULT_ENTRYVIEW);
            myView.getListView().setCellFactory(c->new DraggableAddCell<SpriteDefinition>(mySelected.getListView()));
            myViews.add(myView);
            TitledPane tp = new TitledPane(def.getTitle(), myView.getListView());
            myAccordion.getPanes().add(tp);
        }
        mySelected.getListView().setCellFactory(c->new DraggableRemoveCell<SpriteDefinition>(myAccordion));
        mySelected.getListView().setPlaceholder(new Label("Drag Sprites Here"));
        myContainer = myUIFactory.makeHBox(10, Pos.CENTER, myAccordion, mySelected.draw());
    }

    /**
     * Returns a list of selected sprites to be added to the given group
     * 
     * @return
     */
    public List<SpriteDefinition> getChosen () {
        return mySelected.getSelected();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
