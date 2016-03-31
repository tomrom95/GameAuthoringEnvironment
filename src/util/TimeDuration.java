package util;

public class TimeDuration {

    private double myDuration;

    public TimeDuration (double millis) {
        myDuration = millis;
    }

    public double getMillis () {
        return myDuration;
    }
    
    public void increase (TimeDuration other) {
        myDuration += other.getMillis();
    }
    
    public void setToZero ()  { 
        myDuration = 0;
    }
}
