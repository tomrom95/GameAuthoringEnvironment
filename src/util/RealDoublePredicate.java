package util;

import java.util.function.DoublePredicate;

public class RealDoublePredicate implements DoublePredicate {

    private DoublePredicate myDoublePredicate;
    
    public RealDoublePredicate (DoublePredicate doublePredicate) {
        myDoublePredicate = doublePredicate;
    }
    @Override
    public boolean test (double value) {
        return myDoublePredicate.test(value);
    }

}
