package waves;

import engine.definitions.SpriteDefinition;

public class SpriteWaveData {
	
	private SpriteDefinition myDefinition;
	private int myCount;
	
	public SpriteWaveData(SpriteDefinition def, int count){
		myDefinition = def;
		myCount = count;
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

}
