package gameauthoring.creation.subforms.movement;

import engine.SpriteGroup;
import gameauthoring.creation.subforms.ISubFormView;


public interface ITrackingMoverSFV extends ISubFormView {

    SpriteGroup getTargetsCoice ();

    void populateWithData (double speed, SpriteGroup targets);

    double getSpeed ();

}
