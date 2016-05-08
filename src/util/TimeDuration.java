// This entire file is part of my masterpiece.
// David Maydew
// I have chosen this class for my masterpiece because although it is simple, it represents a highly
// reusable class that can be easily implemented in any project. It features the implementation of a
// common java interface, easy to follow commenting, and closed code that is open for extension.
// This class came in handy during our project when trying to standardize between using milliseconds
// (which JavaFX uses) and seconds (which a user might typically expect). This code is a good
// example of design in action, as it prevents the common pitfall of passing primitives around and
// relying on the fact that the meaning of those primitives does not change.
package util;

/**
 * This class represents a duration of time, with the precision of milliseconds stored as doubles.
 * Should be used instead of just passing doubles to prevent confusion between milliseconds and
 * seconds.
 *
 * @author David Maydew
 *
 */
public class TimeDuration implements Comparable<TimeDuration> {

    private static final int MILLIS_PER_SEC = 1000;
    private double myDuration;

    /**
     * Constructs a TimeDuration object starting with a default time of 0
     */
    public TimeDuration () {
        this(0);
    }

    /**
     * Constructs a TimeDuration object with a given starting millisecond value
     * 
     * @param millis for starting value
     */
    public TimeDuration (double millis) {
        myDuration = millis;
    }

    /**
     * @return the number of milliseconds represented by this object
     */
    public double getMillis () {
        return myDuration;
    }

    /**
     * @return the number of seconds represented by this object
     */
    public double getSeconds () {
        return getMillis() / MILLIS_PER_SEC;
    }

    /**
     * Increase the time count by the amount of a given TimeDuration object
     * 
     * @param other TimeDuration to increase by
     */
    public void increase (TimeDuration other) {
        myDuration += other.getMillis();
    }

    /**
     * Resets the time stored by this class to 0
     */
    public void setToZero () {
        myDuration = 0;
    }

    @Override
    public int compareTo (TimeDuration other) {
        return Double.compare(getMillis(), other.getMillis());
    }
}
