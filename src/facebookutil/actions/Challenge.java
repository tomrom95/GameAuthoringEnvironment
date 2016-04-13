package facebookutil.actions;

import facebookutil.IUser;

public interface Challenge extends SocialAction {
    
    public void createChallenge (IUser source, IUser target, String message);

}
