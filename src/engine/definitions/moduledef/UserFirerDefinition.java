package engine.definitions.moduledef;

import engine.Positionable;
import engine.modules.IModule;

public class UserFirerDefinition extends FirerDefinition {

    private String increase;
    private String decrease;
    private String fire;
    private double angleStep;
    
    
    public UserFirerDefinition () {
        // TODO Auto-generated constructor stub
    }

    @Override
    public IModule create (Positionable parent) {
        // TODO Auto-generated method stub
        return null;
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
