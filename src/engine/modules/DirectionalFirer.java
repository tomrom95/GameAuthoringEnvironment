package engine.modules;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IGame;
import engine.IPositionable;
import engine.definitions.SpriteDefinition;
import engine.sprite.ISprite;
import util.Coordinate;
import util.TimeDuration;

public class DirectionalFirer extends Firer {
    
    private IGame myGame;
    private IAttribute myWaitTime;
    private TimeDuration lastFire;
    private IPositionable mySprite;
    private SpriteDefinition myProjectile;
    //should this (below) be an attribute?
    private double myAngle;
    double timeSinceFire;



    public DirectionalFirer (IGame game, SpriteDefinition projectile, IPositionable sprite, double waitTime, double theta){
        
        myGame = game;
        myWaitTime = new Attribute(waitTime, AttributeType.FIRE_RATE);
        lastFire = new TimeDuration(0);
        mySprite = sprite;
        myProjectile = projectile;
        myAngle = theta;
        timeSinceFire = 0;
        
    }
    @Override
    public void update (TimeDuration duration) {
        fire(duration);
    }

    private void fire (TimeDuration duration) {
        
//        if((duration.getMillis() - lastFire.getMillis()) >= myWaitTime.getValueProperty().get()){
        timeSinceFire += duration.getMillis();
        
        if(timeSinceFire >= myWaitTime.getValueProperty().get()){
            timeSinceFire = 0;
           
            ISprite bullet = myProjectile.create();
            bullet.setLocation(new Coordinate(mySprite.getLocation().getX(), mySprite.getLocation().getY()));
           
            bullet.getMovementStrategy().setXVel(getXVel(myAngle));
            bullet.getMovementStrategy().setYVel(getYVel(myAngle));            
            myGame.bufferedAdd(bullet);
            lastFire = new TimeDuration(duration.getMillis());
            
        } 
    }
    
    private double getXVel(double theta){
        return myProjectile.getMovementDefinition().getSpeed() * Math.cos(theta * Math.PI / 180);
    }
    
    private double getYVel(double theta){
     
        return myProjectile.getMovementDefinition().getSpeed() * Math.sin(theta * Math.PI / 180);        
    }

}
