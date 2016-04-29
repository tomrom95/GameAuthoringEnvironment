package util;

public class SampledBitMap extends SetBitMap implements ISampledBitMap  {

    
    private static final double DEFAULT_HEIGHT = 100;
    private static final double DEFAULT_WIDTH = 100;
    
    private double myTrueHeight;
    private double myTrueWidth;
    
    public SampledBitMap (int gridHeight, int gridWidth) {
        this(gridHeight, gridWidth, DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public SampledBitMap (int gridHeight, int gridWidth, double trueHeight, double trueWidth) {
        super(gridHeight, gridWidth);
        setTrueWidth(trueWidth);
        setTrueHeight(trueHeight);
    }

    @Override
    public double trueWidth () {
        return myTrueWidth;
    }

    @Override
    public double trueHeight () {
        return myTrueHeight;
    }

    private void setTrueWidth (double width) {
        myTrueWidth = width;
    }

    private void setTrueHeight (double height) {
        myTrueHeight = height;
    }

    @Override
    public boolean translatedValueOf (double virtualX, double virtualY) {
        return valueOf((int) virtualX / widthScale(), (int) virtualY / heightScale());
    }

    @Override
    public void translatedSet (double virtualX, double virtualY, boolean toSet) {
        set((int) virtualX / widthScale(), (int) virtualY / heightScale(), toSet);
    }

    @Override
    public boolean translatedValueOf (Coordinate coord) {
        return translatedValueOf(coord.getX(), coord.getY());
    }

    @Override
    public int widthScale () {
        int toReturn = (int) (trueHeight() / getHeight());
        return toReturn > 0 ? toReturn : 1;
    }

    @Override
    public int heightScale () {
        int toReturn = (int) (trueWidth() / getWidth());
        return toReturn > 0 ? toReturn : 1;
    }

}
