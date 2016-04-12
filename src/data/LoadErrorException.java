package data;

/**
 * This error class is responsible for managing errors responsible with invalid or erroneous file
 * selection
 *
 */
public class LoadErrorException extends Exception {

    public LoadErrorException () {
        // TODO Auto-generated constructor stub
    }

    public LoadErrorException (String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public LoadErrorException (Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public LoadErrorException (String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public LoadErrorException (String message,
                               Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
