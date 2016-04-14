package util;

/**
 * This class represents a duration of time, stored with millisecond precision.
 * 
 * @author David Maydew
 *
 */
public class TimeDuration {

    private double myDuration;

    public TimeDuration () {
        myDuration = 0;
    }
    
    public TimeDuration (double millis) {
        myDuration = millis;
    }

    public double getMillis () {
        return myDuration;
    }

    public double getSeconds () {
        return getMillis() / 1000;
    }

    public void increase (TimeDuration other) {
        myDuration += other.getMillis();
    }

    public void setToZero () {
        myDuration = 0;
    }
}
