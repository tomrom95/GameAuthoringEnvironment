package util;

import java.util.function.DoublePredicate;
import util.predicates.EqualsDoublePredicate;
import util.predicates.GreaterThanDoublePredicate;
import util.predicates.LessThanDoublePredicate;


public class PredicateGenerator {

    public DoublePredicate generateDouble (String value, String type) {
        double valToCompare = Double.parseDouble(value);
        if (type.equals("==")) {
            return new EqualsDoublePredicate(valToCompare);
        }
        else if (type.equals(">")) {
            return new GreaterThanDoublePredicate(valToCompare);
        }
        else {
            return new LessThanDoublePredicate(valToCompare);
        }

    }
}
