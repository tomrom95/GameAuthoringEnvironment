package engine.rendering;

public class GameGridConfigNonScaling implements IGameGridConfig {
    private int myGridWidth;
    private int myGridHeight;

    public GameGridConfigNonScaling (int virtualWidth, int virtualHeight) {
        myGridWidth = virtualWidth;
        myGridHeight = virtualHeight;
    }

    @Override
    public int getGridWidth () {
        return myGridWidth;
    }

    @Override
    public int getGridHeight () {
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
