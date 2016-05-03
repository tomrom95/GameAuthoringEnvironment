package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import javafx.beans.property.BooleanProperty;


/**
 * Defines necessary information methods to update an Upgrade Module Definition Properly
 *
 * @author Joe Lilien
 *
 */

public interface IUpgradeSFV extends ISubFormView {

    BooleanProperty isUpgradableProperty ();

    BooleanProperty isGlobalProperty ();

    SpriteDefinition getNextUpgrade ();

    AttributeDefinition getDepletedAttribute ();

    double getMyCost ();

    void populateWithData (boolean isUpgradable,
                           SpriteDefinition nextUpgrade,
                           AttributeDefinition depletedAttribute,
                           double cost);
}
