package gameauthoring.creation.subforms;

import engine.IGame;
import engine.SpriteGroup;

public class GroupSFCFactory extends SubFormControllerFactory<SpriteGroup>{

    public GroupSFCFactory (IGame game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ISubFormController<SpriteGroup> createSubFormController (String type) {
        if (type.equals("SelectSprite")) {
            return new SelectSpriteSFC(getMyAuthorshipData().getMyCreatedSprites());
        }        
        return null;
    }

}
