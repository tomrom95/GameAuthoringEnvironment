package util;

import gameauthoring.util.ErrorMessage;

public class StringParser {

    
    private static final int DEFAULT_RETURN = 0;
    
    public double parseDouble (String input) {
        try {
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            showError(e);
            return DEFAULT_RETURN;
        }
    }
    
    private void showError (NumberFormatException e) {
        new ErrorMessage (e.getMessage()).showError();
    }

    public int parseInt (String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            new ErrorMessage (e.getMessage()).showError();
            return DEFAULT_RETURN;
        }
    }
}
