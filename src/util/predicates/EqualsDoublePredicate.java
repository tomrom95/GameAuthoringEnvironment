package util.predicates;

public class EqualsDoublePredicate extends RealDoublePredicate {

    public EqualsDoublePredicate (double valToCompare) {
        super(valToCompare);
    }

    @Override
    public boolean test (double value) {
        return Double.compare(getValToCompare(), value) == 0;
    }

}
