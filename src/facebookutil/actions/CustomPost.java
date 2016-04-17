package facebookutil.actions;

import facebookutil.user.SocialProfile;

/**
 * This interface is responsible for handling user generated custom posts
 * 
 *
 */
public interface CustomPost extends Post {

    public void createPost (String message, SocialProfile profile);

}
