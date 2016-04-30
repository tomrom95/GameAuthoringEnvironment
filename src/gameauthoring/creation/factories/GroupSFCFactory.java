package gameauthoring.creation.factories;

import engine.IGame;
import engine.SpriteGroup;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.SelectSpriteSFC;

public class GroupSFCFactory extends SubFormControllerFactory<SpriteGroup>{

    public GroupSFCFactory (IGame game) {
        super(game);
    }

    @Override
    protected ISubFormController<SpriteGroup> createSubFormController (String type, Object ... params) {
        if (type.equals("SelectSprite")) {
            return new SelectSpriteSFC(getMyAuthorshipData());
        }        
        throw new ReflectionException("Can't create group subformcontroller of type " + type);
    }

}
