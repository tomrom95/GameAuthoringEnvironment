package usecases;

import engine.IAttribute;
import engine.IEffect;
import engine.IGlobalEffect;
import engine.IMovementModule;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.Coordinate;
import util.TimeDuration;

/**
 * Demonstrates a user controlled movement module 
 * @author RyanStPierre
 */


public class UserControlledUseCase {

    IMovementModule mover;

    public UserControlledUseCase () {
        String right = "d";
        String left = "s";
        mover = new Mover(left, right);
    }

    public class Mover implements IMovementModule {

        public static final double SPEED_CHANGE = 1;
        IAttribute myXVel;
        IAttribute myYVel;
        ObjectProperty<Coordinate> myLocation;

        /**
         * String used to represent keys at this stage. This could easily be changed to be more
         * robust
         *
         */
        private String myRightKey;
        private String myLeftKey;

        public Mover (String left, String right) {
            myLocation = new SimpleObjectProperty<>();
            myRightKey = right;
            myLeftKey = left;
        }

        @Override
        public void applyEffect (IEffect effect) {

            if (effect.getAttributeType().equals(myXVel.getType())) {
                effect.applyToAttribute(myXVel);
            }
            else if (effect.getAttributeType().equals(myYVel.getType())) {
                effect.applyToAttribute(myYVel);
            }
        }

        @Override
        public void update (TimeDuration duration) {
            // TODO Auto-generated method stub
            double xChange = myXVel.getValueProperty().get() * duration.getMillis();
            double yChange = myYVel.getValueProperty().get() * duration.getMillis();
            applyChange(myXVel, xChange);
            applyChange(myYVel, yChange);
            myLocation.get().setLocation(myXVel.getValueProperty().get(),
                                         myYVel.getValueProperty().get());
        }

        private void applyChange (IAttribute attribute, double change) {
            attribute.getValueProperty().set(attribute.getValueProperty().get() + change);
        }

        @Override
        public void applyEffect (IGlobalEffect effect) {
            if (effect.isKeyInput()) {
                if (effect.getKey().isEqual(myRightKey)) {
                    setValue(myXVel, myXVel.getValueProperty().get() + SPEED_CHANGE);
                }
                else if (effect.getKey().isEqual(myLeftKey)) {
                    setValue(myXVel, myXVel.getValueProperty().get() - SPEED_CHANGE);
                }
            }
        }

        private void setValue (IAttribute attribute, double value) {
            attribute.setValue(value);

        }
    }

}
