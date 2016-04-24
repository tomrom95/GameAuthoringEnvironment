package engine.definitions.moduledef;

import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.modules.IModule;
import engine.modules.UserFirer;
import util.Key;

public class UserFirerDefinition extends DirectionalFirerDefinition {

    private String increase;
    private String decrease;
    private String fire;
    private double angleStep;
    

    @Override
    public UserFirer create (Positionable parent) {
        UserFirer myFirer = new UserFirer( parent,
                getProjectileDefinition(),
                new Key(fire),
                new Key(increase),
                new Key (decrease),
                getWaitTime(),
                getAngle(),
                angleStep);
        
        setSuperVariables(myFirer);
        
        return myFirer;
        
        
    }
    
    
    public void setIncrease(String newIncrease){
        increase = newIncrease;
        
    }
    
    public String getIncrease(){
        return increase;
    }
    
    public void setDecrease(String newDecrease){
        decrease = newDecrease;
    }
    
    public String getDecrease(){
        return decrease;
    }
    
    public void setFire(String newFire){
        fire = newFire;
    }
    
    public String getFire(){
        return fire;
    }
    
    public void setAngleStep(double newStep){
        angleStep = newStep;
    }
    
    public double getAngleStep(){
        return angleStep;
    }

}
