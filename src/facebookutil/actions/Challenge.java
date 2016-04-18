package facebookutil.actions;

import facebookutil.user.profiles.UserProfile;


/**
 * This interface extends SocialAction and manages methods specifically for a Challenge action
 *
 */
public interface Challenge extends SocialAction {

    public void createChallenge (UserProfile source, UserProfile target, String message);

}
