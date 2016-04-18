package gameauthoring.creation.subforms;

import engine.AuthorshipData;
import engine.definitions.SpriteDefinition;
import gameauthoring.shareddata.IDefinitionCollection;

public class UpgradeSFC implements ISubFormControllerSprite {
    
    private UpgradeSFV mySFV;

    public UpgradeSFC(AuthorshipData data){
        mySFV = new UpgradeSFV(data); 
    }
    
    @Override
    public void updateItem (SpriteDefinition item) {
//        if(mySFV.isUpgradableProperty().get()){
//            SpriteDefinition nextUpgrade = mySFV.getUpgradeChoices().getSelected();
//            System.out.println(nextUpgrade);
//        }
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        
    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return null;
    }

}
