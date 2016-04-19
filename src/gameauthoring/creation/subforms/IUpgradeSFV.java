package gameauthoring.creation.subforms;

import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import javafx.beans.property.BooleanProperty;

/**
 * Defines necessary information methods to update an Upgrade Module Definition Properly
 * 
 * @author Joe Lilien
 *
 */

public interface IUpgradeSFV {

    BooleanProperty isUpgradableProperty ();

    BooleanProperty isGlobalProperty ();

    void setIsUpgradable (boolean isSelected);

    SpriteDefinition getNextUpgrade ();

    AttributeDefinition getDepeltedAttribute ();

    String getMyCostKey ();
}
