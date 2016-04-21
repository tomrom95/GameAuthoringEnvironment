package util;

import java.util.function.DoublePredicate;

public class PredicateGenerator {

    public DoublePredicate generateDouble (String value, String type) {
        
        double valToCompare = Double.parseDouble(value);
        if (type.equals("==")) {
            return new RealDoublePredicate(val -> val == valToCompare);
        }
        else if (type.equals(">")) {
            return new RealDoublePredicate(val -> val > valToCompare);
        }
        else if (type.equals("<")) {
            return new RealDoublePredicate(val -> val < valToCompare);
        }
        else if (type.equals(">=")) {
            return new RealDoublePredicate(val -> val >= valToCompare);
        }
        else {
            return new RealDoublePredicate(val -> val <= valToCompare);
        }
        
    }
}
