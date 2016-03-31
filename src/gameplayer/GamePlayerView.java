package gameplayer;

public interface GamePlayerView {
    /*
     * has LevelView, HUD, and TowerPicker
     */
    public HeadsUpDisplay getHUD();
    
    public LevelView getLevelView();
    
    public SpritePicker getSpritePicker();
}
