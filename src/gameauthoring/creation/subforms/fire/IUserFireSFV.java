package gameauthoring.creation.subforms.fire;

import engine.SpriteGroup;
import engine.definitions.concrete.SpriteDefinition;

public interface IUserFireSFV extends IFiringSFV {
    String getMyIncrease();
    
    String getMyDecrease();
    
    String getMyFire();
    
    double getMyAngle();
    
    double getMyAngleStep();
    
    void populateWithData (SpriteDefinition missile, double waitTime, double range, boolean isRanged, double angle, double anglestep, String increase, String decrease, String fire);


}
