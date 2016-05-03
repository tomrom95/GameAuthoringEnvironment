package gameauthoring.creation.subforms;

import engine.definitions.concrete.SpriteDefinition;


public class AISpriteSFC implements ISubFormControllerSprite {

    private AISpriteSFV myView;

    public AISpriteSFC () {
        myView = new AISpriteSFV();
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        if (myView.getIsAI()) {
            if (myView.getIsObstructable()) {
                item.setObstructability();
            }
            else if (myView.getIsGoal()) {
                item.setMyGoal();
            }
            else {
                item.clearGoalObstructable();
            }
        }
        else {
            item.clearGoalObstructable();
        }
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        myView.populateWithData(item.getObstructability(), item.isMyGoal(),
                                (item.getObstructability() | item.isMyGoal()));
    }

    @Override
    public void initializeFields () {
        myView.populateWithData(true, false, false);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
