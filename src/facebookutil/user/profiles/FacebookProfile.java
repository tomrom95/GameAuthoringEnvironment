package facebookutil.user.profiles;

import facebookutil.actions.facebook.FacebookCustomPost;
import facebookutil.user.IUser;

public class FacebookProfile extends UserProfile{

    public FacebookProfile (String userID) {
        super(userID);
    }

    @Override
    public void challenge (IUser target, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void customPost (String message) {
        FacebookCustomPost post = new FacebookCustomPost();
        post.createPost(message, this);
        post.send(getLogin().getService(), getLogin().getToken());
    }

    @Override
    public void highScorePost (IUser user, int score) {
        // TODO Auto-generated method stub
        
    }

}
