package util.predicates;

import java.util.function.DoublePredicate;

public abstract class RealDoublePredicate implements DoublePredicate {
    
    private double myValToCompare;
    
    public RealDoublePredicate (double valToCompare) {
        myValToCompare = valToCompare;
    }
    
    protected double getValToCompare() {
        return myValToCompare;
    }

}
