package engine;

import javafx.scene.Node;
import javafx.scene.image.Image;

public interface IGraphicFactory {

	Node getVisual (Block block);
	
	Node getVisual (Image image);
	
	Node getVisual (TextGraphic text);
	
}
