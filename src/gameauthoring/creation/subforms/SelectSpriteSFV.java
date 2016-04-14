package gameauthoring.creation.subforms;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import java.util.*;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;


public class SelectSpriteSFV extends SubFormView {
    private Accordion myContainer;
    private List<MultiChoiceEntryView<SpriteDefinition>> myViews;

    public SelectSpriteSFV (List<DefinitionCollection<SpriteDefinition>> sprites) {
        myContainer = new Accordion();
        myViews = new ArrayList<>();
        initContainer(sprites);
    }

    private void initContainer (List<DefinitionCollection<SpriteDefinition>> sprites) {
        for (IDefinitionCollection<SpriteDefinition> def : sprites) {
            MultiChoiceEntryView<SpriteDefinition> myView =
                    new MultiChoiceEntryView<>(def.getTitle(), def.getItems(), 300, 400,
                                               AuthoringView.DEFAULT_ENTRYVIEW);
            myViews.add(myView);
            // TODO: custom cell
            // spr.setCellFactory(c-> new );

            TitledPane tp = new TitledPane(def.getTitle(), myView.getListView());
            myContainer.getPanes().add(tp);
        }
    }

    public List<SpriteDefinition> getChosen () {
        List<SpriteDefinition> list = new ArrayList<>();
        for (MultiChoiceEntryView<SpriteDefinition> view : myViews) {
            list.addAll(view.getSelected());
        }
        return list;
    }

    public void setChosen (List<SpriteDefinition> list) {
        // TODO: remove duplicated loops with lambda/reflection

        for (MultiChoiceEntryView<SpriteDefinition> view : myViews) {
            view.clearSelection();
        }
        for (SpriteDefinition spr : list) {
            for (MultiChoiceEntryView<SpriteDefinition> view : myViews) {
                if (view.getListView().getItems().contains(spr)) {
                    view.select(spr);
                }
            }
        }
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
