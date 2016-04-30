package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.subforms.ISubFormView;


public interface IUserMoverSFV extends ISubFormView {

    double getSpeed ();

    String getUpKey ();

    String getDownKey ();

    String getLeftKey ();

    String getRightKey ();

    void populateWithData (double speed, String up, String down, String left, String right);

}
