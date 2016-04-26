package util.predicates;

public class LessThanDoublePredicate extends RealDoublePredicate {
    
    public LessThanDoublePredicate (double valToCompare) {
        super(valToCompare);
    }

    @Override
    public boolean test (double value) {
        return value < getValToCompare();
    }
    
    

}
