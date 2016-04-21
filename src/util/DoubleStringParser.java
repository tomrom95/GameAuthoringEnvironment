package util;

import gameauthoring.util.ErrorMessage;

public class DoubleStringParser {

    
    private static final double DEFAULT_RETURN = 0;
    
    public double parse (String input) {
        try {
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            new ErrorMessage (e.getMessage()).showError();
            return DEFAULT_RETURN;
        }
    }
}
