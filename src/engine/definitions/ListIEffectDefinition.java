package engine.definitions;

import java.util.List;
import engine.effects.IEffect;
import engine.profile.IProfile;


public class ListIEffectDefinition  {

    private List<IEffect> myEffectList;

    public List<IEffect> create () {
        return myEffectList;
    }

    public void setMyEffectList (List<IEffect> effectList) {
        myEffectList = effectList;
    }

    public List<IEffect> getMyEffectList () {
        return myEffectList;
    }

}
