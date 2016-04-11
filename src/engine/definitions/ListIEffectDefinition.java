package engine.definitions;

import java.util.List;
import engine.effects.IEffect;
import engine.profile.IProfile;


public class ListIEffectDefinition implements IDefinition {

    private List<IEffect> myEffectList;
    private IProfile myProfile;

    public List<IEffect> create () {
        return myEffectList;
    }

    public void setMyEffectList (List<IEffect> effectList) {
        myEffectList = effectList;
    }

    public List<IEffect> getMyEffectList () {
        return myEffectList;
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
