package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.definitions.EventPackageDefinition;

public class CreationControllerEvent extends CreationController<EventPackageDefinition>{

    public CreationControllerEvent (String title,
                                    List<String> sfcs,
                                    Game game) {
        super(title, sfcs, game);
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
