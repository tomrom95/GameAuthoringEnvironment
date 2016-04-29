package gameauthoring.creation.subforms.fire;

import gameauthoring.creation.subforms.ISubFormView;
import javafx.beans.property.BooleanProperty;

/**
 * Generic interface for all firing subform views.  As of now contains the method to store and pass data on ranged firing
 * 
 * @author josephtimko1
 *
 */

//TODO: get rid of unecessary methods when done
//TODO: move waittime into this interface
public interface IFiringSFV extends ISubFormView {

      String getMyRangedKey();
      
      String getMyRangeValueKey();
      
      double getMyRange();
      
      boolean getMyIsRanged();
      
      double getMyWaitTime ();
      
      BooleanProperty isRangedProperty ();
}
