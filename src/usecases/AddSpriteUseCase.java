package usecases;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import engine.ISprite;
import engine.SpriteType;
import gameauthoring.FormData;
import gameauthoring.ISpriteEditorController;
import gameauthoring.SpriteListHolder;
import gameauthoring.SubFormController;
import gameauthoring.SubFormView;
import javafx.collections.ObservableList;


/**
 * This class demonstrates what happens when a user clicks "Create Sprite"
 * 
 * The view will notify the SpriteEditorController that the user clicked "Create
 * Sprite". The SpriteEditorController will create a new ISprite, and all
 * all of the subform controllers to edit the sprite based on the data in the subform
 * An example subform controller (profile subform) is implemented. The SpriteEditorController
 * will then add the sprite to its SpriteListHolder, which will automatically
 * change all views that use the observable list of sprites contained in the SpriteListHolder.
 * 
 * 
 * @author Jeremy Schreck
 *
 */
public class AddSpriteUseCase {

    public AddSpriteUseCase () {

        ISubFormController profileFormController = new ProfileSubFormController();
        SpriteEditorController spriteEditorController = new SpriteEditorController();
        spriteEditorController.addSubFormController(profileFormController);

        // the view will call this when user clicks createSprite
        spriteEditorController.createSprite();

    }

    public class ProfileSubFormController implements ISubFormController {

        private IEntryView myProfileFormView;

        public ProfileSubFormController () {
            myProfileFormView = EasyMock.createMock(IEntryView.class);
        }

        @Override
        public void editSpriteWithData (ISprite sprite) {
            List<FormData> profileFormData = myProfileFormView.getData();
            FormData imageData = profileFormData.get(0);
            FormData nameData = profileFormData.get(1);

            sprite.getProfileProperty().get().getImageFilepathProperty().set(imageData.getData());
            sprite.getProfileProperty().get().getSpriteTypeProperty()
                    .set(new SpriteType(nameData.getData()));

        }
    }

    public class SpriteEditorController implements ISpriteEditorController {

        private List<ISubFormController> mySubFormControllers;
        private SpriteListHolder mySprites;

        private SpriteEditorController () {
            mySubFormControllers = new ArrayList<ISubFormController>();

        }

        @Override
        public void setSprites (SpriteListHolder sprites) {
            // TODO Auto-generated method stub

        }

        @Override
        public void showAndEdit () {
            // TODO Auto-generated method stub

        }

        @Override
        public void deleteSprite () {
            // TODO Auto-generated method stub

        }

        @Override
        public void editSprite (List<IEntryView> subForms) {
            // TODO Auto-generated method stub

        }

        @Override
        public void createSprite () {
            ISprite sprite = EasyMock.createMock(ISprite.class);
            for (ISubFormController subFormController : getMySubFormControllers()) {
                subFormController.editSpriteWithData(sprite);
            }
            addSprite(sprite);

        }

        private List<ISubFormController> getMySubFormControllers () {
            return mySubFormControllers;
        }

        private void addSprite (ISprite sprite) {
            mySprites.addSprite(sprite);
        }

        private ObservableList<ISprite> getMySprites () {
            return mySprites.getSprites();
        }

        public void addSubFormController (ISubFormController subFormController) {
            mySubFormControllers.add(subFormController);
        }
    }
}
