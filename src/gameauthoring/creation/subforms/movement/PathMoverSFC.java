package gameauthoring.creation.subforms.movement;

import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.PathMoverDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class PathMoverSFC implements ISubFormControllerSprite {

    private IPathMoverSFV myView;
    private double myDefaultSpeed = 0;

    public PathMoverSFC () {
        this.myView = new PathMoverSFV();
    }

    @Override
    public void initializeFields () {
        myView.populateWithData(myDefaultSpeed);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        PathMoverDefinition movDef = new PathMoverDefinition();
        movDef.setSpeed(myView.getMySpeed());
        item.setMovementDefinition(movDef);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        PathMoverDefinition movDef = (PathMoverDefinition) item.getMovementDefinition();
        myView.populateWithData(movDef.getSpeed());
    }

}
