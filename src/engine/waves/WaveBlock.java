package engine.waves;

import engine.definitions.concrete.SpriteDefinition;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import util.TimeDuration;

public class WaveBlock {
	
	private SpriteDefinition myDefinition;
	private int myCount;
	private TimeDuration myGapTime;
	
	public WaveBlock(SpriteDefinition def, int count, double gap){
		myDefinition = def;
		myCount = count;
		myGapTime = new TimeDuration(gap);
	}
	
	protected int getCount(){
		return myCount;
	}
	
	protected void setCount(int newCount){
		myCount = newCount;
	}
	
	protected SpriteDefinition getDefinition(){
		return myDefinition;
	}
	
	protected TimeDuration getGap(){
		return myGapTime;
	}
	
	public ISprite spawn () {
	    myCount--;
	    return myDefinition.create();
	}
	
	public ISprite spawnInfinite(){
	    return myDefinition.create();
	}
	
	public boolean isComplete () {
	    return myCount <= 0;
	}
	

}
