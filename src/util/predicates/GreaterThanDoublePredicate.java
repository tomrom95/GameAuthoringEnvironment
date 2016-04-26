package util.predicates;

public class GreaterThanDoublePredicate extends RealDoublePredicate {
    
    public GreaterThanDoublePredicate (double valToCompare) {
        super(valToCompare);
    }

    @Override
    public boolean test (double value) {
        return value > getValToCompare();
    }
    
    
}
