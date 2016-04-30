package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.subforms.ISubFormView;


public interface IConstantMoverSFV extends ISubFormView {

    double getMySpeed ();

    double getMyOrientation ();

    void populateWithData (double orientation, double speed);

}
