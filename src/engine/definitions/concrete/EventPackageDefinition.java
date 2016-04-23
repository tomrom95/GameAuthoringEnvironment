package engine.definitions.concrete;

import java.util.ArrayList;
import java.util.List;
import engine.IEventPackage;
import engine.ISpriteGroup;
import engine.effects.IEffect;
import engine.events.EventPackage;
import engine.events.GameEvent;
import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.profile.Profile;


/**
 * This class represents a definition of an event package, which is often applied as a result of a
 * condition.
 *
 */
public class EventPackageDefinition implements IProfilable {

    private ISpriteGroup mySpriteGroup;
    private List<IEffect> myEffectsList;
    private List<GameEvent> myEventsList;
    private IProfile myProfile;

    public EventPackageDefinition () {
        // TODO add to resource file
        myProfile = new Profile("Event", "Basic Event", "images/Square.png");
        myEffectsList = new ArrayList<>();
        myEventsList = new ArrayList<>();
    }

    public IEventPackage create () {
        return new EventPackage(mySpriteGroup, myEffectsList, myEventsList);
    }

    public ISpriteGroup getMySpriteGroup () {
        return mySpriteGroup;
    }

    public void setMySpriteGroup (ISpriteGroup spriteGroup) {
        mySpriteGroup = spriteGroup;
    }

    public List<IEffect> getMyEffectsList () {
        return myEffectsList;
    }

    public void setMyEffectsList (List<IEffect> effectList) {
        myEffectsList = effectList;
    }

    public List<GameEvent> getMyEventsList () {
        return myEventsList;
    }

    public void setMyEventsList (List<GameEvent> eventList) {
        myEventsList = eventList;
    }

    @Override
    public IProfile getProfile () {
        return myProfile;
    }

    @Override
    public void setProfile (IProfile profile) {
        myProfile = profile;
    }

}
