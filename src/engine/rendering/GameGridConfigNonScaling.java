package engine.rendering;

public class GameGridConfigNonScaling implements IGameGridConfig {
    private double myGridWidth;
    private double myGridHeight;
    
    public GameGridConfigNonScaling (double virtualWidth, double virtualHeight) {
        myGridWidth = virtualWidth;
        myGridHeight = virtualHeight;
    }
    

    @Override
    public double getGridWidth () {
        return myGridWidth;
    }

    @Override
    public double getGridHeight () {
        return myGridHeight;
    }

    @Override
    public double getXScalingFactor () {
        return 1;
    }

    @Override
    public double getYScalingFactor () {
        return 1;
    }


    @Override
    public void setXScalingFactor (double scaleX) {
    }

    @Override
    public void setYScalingFactor (double scaleY) {
    }

}
