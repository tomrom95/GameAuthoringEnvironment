package engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;


/**
 * A check implemented to target a specific attribute type. If the attribute type if found
 * and greater than the cost, the cost is subtracted and check returns true.
 * 
 * @author RyanStPierre
 *
 */
public class Check implements ICheck {
    private static final double THRESH = -.001;

    private IAttributeManager myManager;
    private AttributeType myType;
    private double myCost;
    private BooleanProperty myStatus;
    private ChangeListener<Number> costListener;

    public Check (IAttributeManager attributeManager, AttributeType type, double cost) {
        System.out.println("making");
        myManager = attributeManager;
        myType = type;
        myCost = cost;
        myStatus = new SimpleBooleanProperty(false);
    }

    public void alterAttribute () {

        for (IAttribute attribute : myManager.getAttributes()) {
            if (attribute.getType().equals(myType)) {
                attribute.setValue(attribute.getValueProperty().get() - myCost);
            }
        }

    }

    private void addStatus () {
        for (IAttribute attribute : myManager.getAttributes()) {
            if (attribute.getType().toString().equals(myType.toString())) {
                if (costListener != null) {
                    attribute.getValueProperty().removeListener(costListener);
                }
                costListener = (obs, oldValue, newValue) -> checkPrice(attribute);
                attribute.getValueProperty().addListener(costListener);
                checkPrice(attribute);
                return;
            }
        } 
        myStatus.set(false);
    }

    private void checkPrice (IAttribute attribute) {
        if (attribute.getValueProperty().get() - myCost > THRESH) {
            myStatus.set(true);
            return;
        }
        myStatus.set(false);
    }

    @Override
    public BooleanProperty getStatus () {
        addStatus();
        return myStatus;
    }

}
