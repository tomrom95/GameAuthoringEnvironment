package engine.definitions;

import java.util.List;
import engine.effects.IEffect;


public class ListIEffectDefinition  {

    private List<IEffect> myEffectList;

    public List<IEffect> create () {
        return myEffectList;
    }

    public void setMyEffectList (List<IEffect> effectList) {
        myEffectList = effectList;
    }
}
