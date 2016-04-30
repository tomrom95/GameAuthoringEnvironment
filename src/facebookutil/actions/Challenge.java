package facebookutil.actions;

import facebookutil.applications.App;
import facebookutil.user.profiles.UserProfile;


/**
 * This helps users challenge users. In the case of facebook, this is a facebook game request.
 * In something else like twitter, it might be tweeting at someone
 *
 */
public interface Challenge extends SocialAction {

    public void createChallenge (App applicaiton, UserProfile target, String message);

}
