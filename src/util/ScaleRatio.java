package util;

public class ScaleRatio {

    private double myRatio;
    
    public ScaleRatio () {
        myRatio = 1;
    }
    
    public ScaleRatio (double scale) {
        myRatio = scale;
    }
    
    
    public double getScale () {
        return myRatio;
    }
    
    
    public void setScale (double scale) {
        myRatio = scale;
    }
}
