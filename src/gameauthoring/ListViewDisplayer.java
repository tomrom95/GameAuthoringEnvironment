package gameauthoring;

import java.util.function.Consumer;

public interface ListViewDisplayer extends Glyph {
    
    void setCreateAction(Consumer<?> action);

}
