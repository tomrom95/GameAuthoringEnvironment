package util;

public class LevelBound {

    private double myWidth;
    private double myHeight;

    public LevelBound ()  {
        myWidth = 0;
        myHeight = 0;
    }
    
    public LevelBound (double width, double height) {
        myWidth = width;
        myHeight = height;
    }

    public double getWidth () {
        return myWidth;
    }

    public double getHeight () {
        return myHeight;
    }
}
