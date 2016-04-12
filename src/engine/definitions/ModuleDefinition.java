package engine.definitions;

import engine.IPositionable;
import engine.modules.IModule;


public abstract class ModuleDefinition implements IDefinition {

    public abstract IModule create (IPositionable parent);

}
