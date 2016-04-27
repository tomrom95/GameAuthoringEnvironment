package gameauthoring.creation.subforms.fire;

import gameauthoring.creation.subforms.ISubFormView;
import javafx.beans.property.BooleanProperty;

public interface IFiringSFV extends ISubFormView {

      String getMyRangedKey();
      
      String getMyRangeValueKey();
      
      BooleanProperty isRangedProperty ();
}
