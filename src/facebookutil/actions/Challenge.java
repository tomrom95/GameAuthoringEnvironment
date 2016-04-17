package facebookutil.actions;

import facebookutil.user.SocialProfile;


/**
 * This interface extends SocialAction and manages methods specifically for a Challenge action
 *
 */
public interface Challenge extends SocialAction {

    public void createChallenge (SocialProfile source, SocialProfile target, String message);

}
