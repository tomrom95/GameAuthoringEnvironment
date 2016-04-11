package engine.definitions;

import engine.modules.IModule;


public abstract class ModuleDefiniton implements IDefinition {

    public abstract IModule create ();
}
