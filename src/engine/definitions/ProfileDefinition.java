package engine.definitions;

public class ProfileDefinition implements IDefinition {

    private String myName;
    private String myDescription;
    private String myURL;

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
}
