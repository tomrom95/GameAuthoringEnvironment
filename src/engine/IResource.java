package engine;

import java.util.List;


public interface IResource {

    List<IAttribute> getAttributes ();

    // TODO add to separate interface
    void update ();
}
