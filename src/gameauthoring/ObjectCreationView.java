package gameauthoring;

public interface ObjectCreationView extends Glyph {
    SpriteListView getSpriteListView ();

    CreationController<?> getCreationController ();
}
