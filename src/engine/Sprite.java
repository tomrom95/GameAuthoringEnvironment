package engine;

import java.awt.Image;
import util.Coordinate;

public class Sprite implements ISpriteEditable, ISpritePlayable {
    
    private IMovementModule myMover;
    private Collection<IModule> myModules;
    private IStatusModule myStatusModule;

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Coordinate getLocation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLocation (Coordinate location) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Image getImage () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setImage (Image image) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IMovementStrategy getMovementStrategy () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMovementStrategy (IMovementStrategy movementStrategy) {
        // TODO Auto-generated method stub
        
    }
    
    

}
