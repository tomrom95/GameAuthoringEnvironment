package util;

public class RGBColor {

    private static final double FULL_PIGMENT = 1;
    private static final double NO_PIGMENT = 0;

    public static final RGBColor WHITE = new RGBColor(FULL_PIGMENT, FULL_PIGMENT, FULL_PIGMENT);

    private double myRed;
    private double myGreen;
    private double myBlue;

    public RGBColor (double red, double green, double blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    private void setRed (double val) {
        myRed = checkUserError(val);
    }

    private void setGreen (double val) {
        myGreen = checkUserError(val);

    }

    private void setBlue (double val) {
        myBlue = checkUserError(val);

    }
    
    public double getRed () {
        return myRed;
    }

    public double getGreen () {
        return myGreen;
    }

    private double getBlue () {
        return myBlue;

    }

    // Possibly throw exception here if desired
    private double checkUserError (double val) {
        if (val < NO_PIGMENT) {
            return NO_PIGMENT;
        }
        else if (val > FULL_PIGMENT) {
            return FULL_PIGMENT;
        }

        return val;
    }

}
