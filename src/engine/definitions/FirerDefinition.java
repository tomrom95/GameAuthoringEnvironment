package engine.definitions;

import engine.IPositionable;
import engine.modules.IModule;

public class FirerDefinition extends ModuleDefinition {

    protected SpriteDefinition myProjectile;
    
    @Override
    public IModule create (IPositionable parent) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    public SpriteDefinition getProjectileDefinition () {
        return myProjectile;
    }
    
    public void setProjectileDefinition (SpriteDefinition projectile) {
        myProjectile = projectile;
    }

}
