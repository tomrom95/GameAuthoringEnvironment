package gameauthoring.creation.subforms;

import engine.SpriteGroup;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import java.util.*;

public class SelectSpriteSFC implements ISubFormControllerGroup{
    
    private SelectSpriteSFV myView;

    public SelectSpriteSFC (List<DefinitionCollection<SpriteDefinition>> sprites) {
        myView = new SelectSpriteSFV(sprites);
    }

    @Override
    public void updateItem (SpriteGroup item) {
        item.setSpriteDefinitions(myView.getChosen());
    }

    @Override
    public void populateViewsWithData (SpriteGroup item) {
        myView.setChosen(item.getSpriteDefinitions());
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
