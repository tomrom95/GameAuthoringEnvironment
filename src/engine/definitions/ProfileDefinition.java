package engine.definitions;

import engine.sprite.Profile;

public class ProfileDefinition implements IDefinition{

    private String myName;
    private String myDescription;
    private String myURL;
    
    public ProfileDefinition(String name, String desc, String url){
        setName(name);
        setDescription(desc);
        setURL(url);
    }

    public void setURL (String url) {
        myURL = url;
    }

    public String getURL () {
        return myURL;
    }

    public void setName (String name) {
        myName = name;
    }

    public void setDescription (String description) {
        myDescription = description;
    }

    public String getName () {
        return myName;
    }

    public String getDescription () {
        return myDescription;
    }
    
    public Profile makeProfile () {
        return new Profile(getName(), getDescription(), getURL());
    }

    @Override
    public ProfileDefinition getProfileDefinition () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        // TODO Auto-generated method stub
        
    }
}
