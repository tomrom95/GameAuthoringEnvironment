package engine;

/**
 * A check implemented to target a specific attribute type.  If the attribute type if found 
 * and greater than the cost, the cost is subtracted and check returns true.
 * @author RyanStPierre
 *
 */
public class Check implements ICheck{

    private IAttributeManager myManager;
    private AttributeType myType;
    private double myCost;
    
    public Check (IAttributeManager attributeManager, AttributeType type, double cost) {
        myManager = attributeManager;
        myType = type;
        myCost = cost;
        
    }
    
    public boolean check () {
        
        for (IAttribute attribute: myManager.getAttributes()) {
            if(attribute.getType().equals(myType) && attribute.getValueProperty().get() >= myCost) {
                attribute.setValue(attribute.getValueProperty().get() - myCost);
                return true;
            }
        }
        
        return false;
    }

    
}
