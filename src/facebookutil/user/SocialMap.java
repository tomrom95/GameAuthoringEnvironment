package facebookutil.user;

import java.util.HashMap;
import java.util.Map;
import facebookutil.SocialType;

public class SocialMap {
    
    private Map<SocialType, SocialProfile> myProfiles;
    private SocialProfile myActiveProfile;
    
    public SocialMap () {
        myProfiles = new HashMap<SocialType, SocialProfile>();
    }
    
    public SocialProfile getProfileByType (SocialType type){
        return myProfiles.get(type);
    }
    
    public boolean setActive (SocialType type) {
        if(!myProfiles.containsKey(type)){
            return false;
        }
        myActiveProfile = myProfiles.get(type);
        return true;
    }
    
    public void createNewProfile (SocialType type, String userID) {
        myProfiles.put(type, new SocialProfile(userID));
    }

    public SocialProfile getActiveProfile () {
        return myActiveProfile;
    }

}
