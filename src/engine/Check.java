package engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import util.TimeDuration;


/**
 * A check implemented to target a specific attribute type. If the attribute type if found
 * and greater than the cost, the cost is subtracted and check returns true.
 * 
 * @author RyanStPierre
 *
 */
public class Check implements ICheck {

    private IAttributeManager myManager;
    private AttributeType myType;
    private double myCost;
    private BooleanProperty myStatus;

    public Check (IAttributeManager attributeManager, AttributeType type, double cost) {
        myManager = attributeManager;
        myType = type;
        myCost = cost;
        myStatus = new SimpleBooleanProperty(false);
        bindStatus(myStatus);
    }

    public void alterAttribute () {

        for (IAttribute attribute : myManager.getAttributes()) {
            if (attribute.getType().equals(myType)) {
                attribute.setValue(attribute.getValueProperty().get() - myCost);
            }

        }

    }

    @Override
    public void update (TimeDuration duration) {
        // Unnecessary now

    }
    
    private void bindStatus (BooleanProperty myStatus2) {
        for (IAttribute attribute : myManager.getAttributes()) {
            if (attribute.getType().equals(myType)) {
                myStatus.bind(attribute.getValueProperty().greaterThanOrEqualTo(myCost));
            }
        }
    }

    @Override
    public BooleanProperty getStatus () {
        System.out.println(myStatus);
        return myStatus;
    }

}
