package gameauthoring.creation.subforms.fire;

import gameauthoring.creation.subforms.ISubFormView;
import javafx.beans.property.BooleanProperty;

/**
 * Generic interface for all firing subform views.  As of now contains the method to store and pass data on ranged firing
 * 
 * @author josephtimko1
 *
 */

public interface IFiringSFV extends ISubFormView {

      String getMyRangedKey();
      
      String getMyRangeValueKey();
      
      BooleanProperty isRangedProperty ();
}
