package engine.definitions;

import engine.modules.IModule;

public abstract class ModuleDefinition implements IDefinition {

    public abstract IModule create ();
}
