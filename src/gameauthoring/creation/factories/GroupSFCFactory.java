package gameauthoring.creation.factories;

import engine.IGame;
import engine.SpriteGroup;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SelectSpriteSFC;

public class GroupSFCFactory extends SubFormControllerFactory<SpriteGroup>{

    public GroupSFCFactory (IGame game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ISubFormController<SpriteGroup> createSubFormController (String type) {
        if (type.equals("SelectSprite")) {
            return new SelectSpriteSFC(getMyAuthorshipData().getMyGroupSprites());
        }        
        return null;
    }

}
