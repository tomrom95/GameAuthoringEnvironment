package engine.definitions;

import java.util.List;
import engine.IEventPackage;
import engine.ISpriteGroup;
import engine.effects.IEffect;
import engine.events.EventPackage;
import engine.events.GameEvent;
import engine.profile.IProfile;


public class EventPackageDefinition implements IDefinition {

    private ISpriteGroup mySpriteGroup;
    private List<IEffect> myEffectsList;
    private List<GameEvent> myEventsList;
    private IProfile myProfile;

    public IEventPackage create () {
        return new EventPackage(mySpriteGroup, myEffectsList, myEventsList);
    }

    public ISpriteGroup getMySpriteGroup () {
        return mySpriteGroup;
    }

    public void setMySpriteGroup (ISpriteGroup spriteGroup) {
        this.mySpriteGroup = spriteGroup;
    }

    public List<IEffect> getMyEffectsList () {
        return myEffectsList;
    }

    public void setMyEffectsList (List<IEffect> effectList) {
        this.myEffectsList = effectList;
    }

    public List<GameEvent> getMyEventsList () {
        return myEventsList;
    }

    public void setMyEventsList (List<GameEvent> eventList) {
        this.myEventsList = eventList;
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
