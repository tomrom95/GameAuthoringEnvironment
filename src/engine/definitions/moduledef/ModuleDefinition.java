package engine.definitions.moduledef;

import engine.Positionable;
import engine.definitions.concrete.IDefinition;
import engine.modules.IModule;


/**
 * This abstract class is the superclass from which all module definitions extend
 * TODO if this has nothing but methods, it should be an interface
 *
 */
public abstract class ModuleDefinition implements IDefinition {

    public abstract IModule create (Positionable parent);

}
