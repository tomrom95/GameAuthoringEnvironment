package gameauthoring.creation.subforms.movement;

import gameauthoring.creation.subforms.ISubFormView;


public interface IPathMoverSFV extends ISubFormView {

    double getMySpeed ();

    void populateWithData (double speed);
}
