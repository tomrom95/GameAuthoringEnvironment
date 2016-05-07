package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.ListWrapper;
import gameauthoring.util.DraggableAddCell;
import gameauthoring.util.DraggableRemoveCell;
import gameauthoring.util.IListWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;


/**
 * Implementation of ISelectSpriteSFV using accoridon and MultiChoiceEntryView
 *
 * @author JoeLilien
 *
 */
public class SelectSpriteSFV extends SubFormView implements ISelectSpriteSFV {
    private static final String MY_TITLE_KEY = "SelectSprite";
    private Accordion myAccordion;
    private HBox myContainer;
    private List<MultiChoiceEntryView<SpriteDefinition>> myViews;
    private List<ListWrapper<SpriteDefinition>> mySprites;
    private MultiChoiceEntryView<SpriteDefinition> mySelected;

    public SelectSpriteSFV (List<ListWrapper<SpriteDefinition>> sprites) {
        setMyTitle(MY_TITLE_KEY);
        myViews = new ArrayList<>();
        mySprites = sprites;
        mySelected =
                new MultiChoiceEntryView<>(getMyLabels().getString("SelectedSpriteLabel"),
                                           FXCollections.observableArrayList(),
                                           200, 400, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    protected void initView () {
        myAccordion = getMyUIFactory().makeAccordion(300);
        for (IListWrapper<SpriteDefinition> def : mySprites) {
            MultiChoiceEntryView<SpriteDefinition> myView =
                    new MultiChoiceEntryView<>(getMyLabels().getString(def.getListMetadata().getTitleKey()),
                                               def.getItems(), 300, 400,
                                               AuthoringView.DEFAULT_ENTRYVIEW);
            myView.getListView()
                    .setCellFactory(c -> new DraggableAddCell<SpriteDefinition>(mySelected
                            .getListView()));
            myViews.add(myView);
            TitledPane tp =
                    new TitledPane(getMyLabels().getString(def.getListMetadata().getTitleKey()),
                                   myView.getListView());
            myAccordion.getPanes().add(tp);
        }
        mySelected.getListView()
                .setCellFactory(c -> new DraggableRemoveCell<SpriteDefinition>(myAccordion));
        mySelected.getListView().setPlaceholder(new Label("Drag Sprites Here"));
        myContainer = getMyUIFactory().makeHBox(10, Pos.CENTER, myAccordion, mySelected.draw());
    }

    /**
     * Returns a list of selected sprites to be added to the given group
     *
     * @return
     */
    @Override
    public List<SpriteDefinition> getChosen () {
        return mySelected.getListView().getItems();
    }

    @Override
    public void setChosen (List<SpriteDefinition> chosenSprites) {
        mySelected.getListView().setItems(FXCollections.observableArrayList(chosenSprites));
    }

    @Override
    public Node draw () {
        return defaultDisplayWithNode(myContainer);
    }

}
