package engine.definitions;

import engine.Positionable;
import engine.modules.Firer;
import engine.modules.IModule;


/**
 * This class represents the definition of a firer module
 *
 */
public abstract class FirerDefinition extends ModuleDefinition {

    private SpriteDefinition myProjectile;
    private double myAmmo;
    private double myRange;
    private boolean ranged;

    @Override
    public abstract IModule create (Positionable parent);

    public SpriteDefinition getProjectileDefinition () {
        return myProjectile;
    }
    
    public void setSuperVariables(Firer myFirer){
    	myFirer.setAmmo(myAmmo);
    	myFirer.setRange(myRange);
    	myFirer.setRanged(ranged);
    }
    
    public void setAmmo(double ammo){
    	myAmmo = ammo;
    }
    
    public double getAmmo(){
    	return myAmmo;
    }

    public void setProjectileDefinition (SpriteDefinition projectile) {
        myProjectile = projectile;
    }
    
    public void setRanged(boolean isRanged){
    	ranged = isRanged;
    }
    
    public boolean getRanged(){
    	return ranged;
    }
    
    public double getFireRange(){
    	return myRange;
    }
    
    public void setFireRange(double range){
    	myRange = range;
    }

}
