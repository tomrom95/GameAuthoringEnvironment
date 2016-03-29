package engine;

import java.util.List;

public interface IRenderer {

    //List used to have sense of order if needed
    void render (List<? extends Drawable> drawables);
}
