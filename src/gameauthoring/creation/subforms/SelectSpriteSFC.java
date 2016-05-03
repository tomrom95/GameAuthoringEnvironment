package gameauthoring.creation.subforms;

import java.util.ArrayList;
import java.util.List;
import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;


public class SelectSpriteSFC implements ISubFormControllerGroup {

    private ISelectSpriteSFV myView;

    public SelectSpriteSFC (AuthorshipData authorshipData) {
        List<DefinitionCollection<SpriteDefinition>> groupableSprites = new ArrayList<>();
        groupableSprites.addAll(authorshipData.getMyCreatedSpritesMap().values());
        groupableSprites.add(authorshipData.getMyCreatedMissiles());
        myView = new SelectSpriteSFV(groupableSprites);
    }

    @Override
    public void updateItem (SpriteGroup item) {
        item.setSpriteDefinitions(myView.getChosen());
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void initializeFields () {
        // No initialization needed
    }

    @Override
    public void populateViewsWithData (SpriteGroup item) {
        myView.setChosen(item.getSpriteDefinitions());
    }

}
