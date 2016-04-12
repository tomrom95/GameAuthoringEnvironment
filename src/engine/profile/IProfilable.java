package engine.profile;

import engine.definitions.IDefinition;

/**
 * 
 * @author Jeremy Schreck, Joe Lilien, Ryan St. Pierre
 *
 */
public interface IProfilable extends IDefinition {
    
    IProfile getProfile ();

    void setProfile (IProfile profile);
}
