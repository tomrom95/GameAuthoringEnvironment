package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class FiringSFCmult implements ISubFormControllerSprite {
    private FiringSFVmult myView;
    private List<ISubFormControllerSprite> mySFCs;
    
    public FiringSFCmult(IGame game){
        myView = new FiringSFVmult(game,this);
        mySFCs = new ArrayList<>();
        
    }
    
    public void addSFC(ISubFormControllerSprite sfc){
        mySFCs.add(sfc);
        myView.addSFV(sfc.getSubFormView());
    }
    
    public void removeSFC(ISubFormControllerSprite sfc){
        mySFCs.remove(sfc);
        myView.removeSFV(sfc.getSubFormView());
    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
        mySFCs.forEach(e->e.updateItem(item));
    }

    @Override
    public void initializeFields () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
