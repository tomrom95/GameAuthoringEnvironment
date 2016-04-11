package engine.definitions;

import engine.ISpriteGroup;


/**
 * We can either have this class and the reflective recursive code generate this stuff
 * on the fly, or we can have it point to a List that has been previously created
 * somewhere in the models for the front end, in another tab/controller/view pair
 * that is dedicated solely to creating and storing the sprite groups
 *
 * Using the index value, or some other form of communication i think will be better
 * 
 * @author jonathanim
 *
 */
public class SpriteGroupDefinition implements IDefinition {

    private int mySpriteGroupIndexVal;
    // private someStateObject myPlaceWhereICanGetSpriteGroups;

    public ISpriteGroup create () {
        return getSpriteGroupForIndex(mySpriteGroupIndexVal);
    }

    private ISpriteGroup getSpriteGroupForIndex (int index) {
        return null;
    }

}
