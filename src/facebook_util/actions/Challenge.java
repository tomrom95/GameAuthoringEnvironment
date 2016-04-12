package facebook_util.actions;

import facebook_util.IUser;

public interface Challenge extends SocialAction {
    
    public void createChallenge (IUser source, IUser target, String message);

}
