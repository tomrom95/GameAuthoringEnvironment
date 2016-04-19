package gameauthoring.creation.subforms;

import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import java.util.*;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.MultiChoiceEntryView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import gameauthoring.tabs.AuthoringView;

/**
 * Implementation of ISelectSpriteSFV using accoridon and MultiChoiceEntryView
 * 
 * @author JoeLilien
 *
 */
public class SelectSpriteSFV extends SubFormView implements ISelectSpriteSFV{
    private Accordion myContainer;
    private List<MultiChoiceEntryView<SpriteDefinition>> myViews;
    private List<DefinitionCollection<SpriteDefinition>> mySprites;

    public SelectSpriteSFV (List<DefinitionCollection<SpriteDefinition>> sprites) {
        myContainer = new Accordion();
        myViews = new ArrayList<>();
        mySprites = sprites;
        initView();
    }
    

    @Override
    protected void initView () {
        for (IDefinitionCollection<SpriteDefinition> def : mySprites) {
            MultiChoiceEntryView<SpriteDefinition> myView =
                    new MultiChoiceEntryView<>(def.getTitle(), def.getItems(), 300, 400,
                                               AuthoringView.DEFAULT_ENTRYVIEW);
            myViews.add(myView);
            TitledPane tp = new TitledPane(def.getTitle(), myView.getListView());
            myContainer.getPanes().add(tp);
        }
    }

    /**
     * Returns a list of selected sprites to be added to the given group
     * 
     * @return
     */
    public List<SpriteDefinition> getChosen () {
        List<SpriteDefinition> list = new ArrayList<>();
        for (MultiChoiceEntryView<SpriteDefinition> view : myViews) {
            list.addAll(view.getSelected());
        }
        return list;
    }


    @Override
    public Node draw () {
        return myContainer;
    }


}
