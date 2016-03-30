package engine;

import java.util.List;


public interface IGamePlayable extends Updateable {

    IGameInformation getGameInformation ();
    
    List<? extends Drawable> getDrawables ();
    
    List<IAttribute> getGlobalAttributes ();

}
