package usecases;

import effects.IEffect;
import engine.IAttribute;
import interactionevents.IInteractionEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modules.IMovementModule;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class demonstrates implementing a user controlled movement module
 * Each Sprite contains a module responsible for its movement. Depending
 * on the logic spelled out in the module the Sprite can move in a variety
 * of ways. I am demonstrating the user controlled case, where the user
 * can define the keys that are clicked to move the Sprite.
 * Possible others include: Path following, AI, simple algorithms
 * 
 * @author RyanStPierre
 */

public class UserControlledUseCase {

    IMovementModule strategy;

    public UserControlledUseCase () {

        /**
         * Right now I am leaving these as Strings. We need to think of a more
         * robust way of doing this. These Strings correspond to the controls
         * the user defines to control movement
         */
        String right = "d";
        String left = "s";

        /**
         * How much the velocity reacts to button input
         */
        double buttonSensitivity = 1;
        strategy = new UserMovement(left, right, buttonSensitivity);
    }

    public class UserMovement implements IMovementModule {

        /**
         * Attributes that store the velocity
         */
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

        /**
         * Attributes that define how sensitive the movement is to the user input
         */
        private IAttribute myButtonSensitivity;

        public UserMovement (String left, String right, double buttonSensitivity) {
            myLocation = new SimpleObjectProperty<>();
            myRightKey = right;
            myLeftKey = left;
            // TODO initialize button sensitivity
        }

        /**
         * Possibly applies effects to all Attributes in class. Effect does internal
         * check to ensure it should be applying its logic to this give AttributeType
         */
        @Override
        public void applyEffect (IEffect effect) {

            effect.applyToAttribute(myButtonSensitivity);
            effect.applyToAttribute(myXVel);
            effect.applyToAttribute(myYVel);
        }

        /**
         * Updates the position of the ISprite based on velocity
         */
        @Override
        public void update (TimeDuration duration) {
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

        /**
         * Takes in global effects (key presses) and updates its velocity Attributes 
         * accordingly 
         */
        @Override
        public void registerEvent (IInteractionEvent effect) {
            if (effect.isKeyInput()) {
                if (effect.getKey().isEqual(myRightKey)) {
                    setValue(myXVel, myXVel.getValueProperty().get() +
                                     myButtonSensitivity.getValueProperty().get());
                }
                else if (effect.getKey().isEqual(myLeftKey)) {
                    setValue(myXVel, myXVel.getValueProperty().get() -
                                     myButtonSensitivity.getValueProperty().get());
                }
            }
        }

        private void setValue (IAttribute attribute, double value) {
            attribute.setValue(value);

        }

        @Override
        public Coordinate getPosition () {
            return myLocation.get();
        }

    }

}
