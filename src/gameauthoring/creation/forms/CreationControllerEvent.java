package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import engine.definitions.EventPackageDefinition;

public class CreationControllerEvent extends CreationController<EventPackageDefinition>{

    public CreationControllerEvent (String title,
                                    List<String> sfcs,
                                    IGame myGame) {
        super(title, sfcs, myGame);
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        authorshipData.setMyCreatedEvents(getMyDefinitionCollection());
        
    }

    @Override
    protected EventPackageDefinition createBlankItem () {
        return new EventPackageDefinition();
    }

}
