package facebookutil.user.profiles;

import facebookutil.user.IUser;

public class LocalProfile extends UserProfile{

    public LocalProfile (String userID) {
        super(userID);
    }

    @Override
    public void challenge (IUser target, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void customPost (String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void highScorePost (IUser user, int score) {
        // TODO Auto-generated method stub
        
    }

}
