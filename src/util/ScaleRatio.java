package util;

/**
 * Scaling class
 * @author RyanStPierre
 *
 */
public class ScaleRatio {

    private double myRatio;

    public ScaleRatio () {
        myRatio = 1;
    }

    public ScaleRatio (double scale) {
        myRatio = scale;
    }

    public double scale (double input) {
        return input * myRatio;
    }

    public double invert (double input) {
        return input / myRatio;
    }

    public void setScale (double scale) {
        myRatio = scale;
    }

    public double getScale () {
        return myRatio;
    }
}
