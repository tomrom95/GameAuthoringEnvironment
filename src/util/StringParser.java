package util;

import gameauthoring.util.ErrorMessage;

public class StringParser {
    
    public double parseDouble (String input) throws NumberFormatException {
        try {
            
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e) {
            showError(e);
            throw e;
        }
    }
    
    private void showError (NumberFormatException e) {
        new ErrorMessage (e.getMessage()).showError();
        
    }

    public int parseInt (String input) throws NumberFormatException {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            showError(e);
            throw e;
        }
    }
}
