package gameauthoring.creation.subforms.fire;

import java.util.*;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;

public class FiringSFCmult implements ISubFormControllerSprite {
    private FiringSFVmult myView;
    private List<RemovableSpriteSFC> mySFCs;
    private List<RemovableSpriteSFC> myRemovedSFCs;
    
    public FiringSFCmult(IGame game){
        myView = new FiringSFVmult(game,this);
        mySFCs = new ArrayList<>();
        myRemovedSFCs = new ArrayList<>();
        
    }
    
    public void addSFC(RemovableSpriteSFC sfc){
        mySFCs.add(sfc);
        myView.addSFV(sfc.getSubFormView());
    }
    
    public void removeSFC(RemovableSpriteSFC sfc){
        mySFCs.remove(sfc);
        myView.removeSFV(sfc.getSubFormView());
        myRemovedSFCs.add(sfc);
    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
        mySFCs.forEach(e->e.updateItem(item));
        myRemovedSFCs.forEach(e->e.removeModule(item));
        //myRemovedSFCs.clear();
        System.out.println(item.getModuleDefinitions());
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
