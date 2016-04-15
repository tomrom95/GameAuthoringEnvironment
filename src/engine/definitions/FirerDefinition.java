package engine.definitions;

import engine.IPositionable;
import engine.modules.IModule;


/**
 * This class represents the definition of a firer module
 *
 */
public class FirerDefinition extends ModuleDefinition {

    private SpriteDefinition myProjectile;

    @Override
    public IModule create (IPositionable parent) {
        // TODO why does this return null?? should this class be abstract?
        return null;
    }

    public SpriteDefinition getProjectileDefinition () {
        return myProjectile;
    }

    public void setProjectileDefinition (SpriteDefinition projectile) {
        myProjectile = projectile;
    }

}
