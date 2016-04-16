package facebookutil.actions;

import facebookutil.user.IUser;


/**
 * This interface extends SocialAction and manages methods specifically for a Challenge action
 *
 */
public interface Challenge extends SocialAction {

    public void createChallenge (IUser source, IUser target, String message);

}
