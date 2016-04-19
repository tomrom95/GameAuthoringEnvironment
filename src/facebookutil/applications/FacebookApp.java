package facebookutil.applications;

import java.util.List;
import facebookutil.actions.facebook.FacebookCustomPost;
import facebookutil.actions.facebook.FacebookNotifyUsers;
import facebookutil.scores.HighScoreBoard;
import facebookutil.user.IUser;
import facebookutil.user.profiles.SocialProfile;

/**
 * Implementation of an app for Facebook.
 * Can login, notify, and (not yet) post to facebook. Still
 * working on that one...
 * @author Tommy
 *
 */
public class FacebookApp extends App{

    @Override
    public void login () {
        FacebookAppLogin appLogin = new FacebookAppLogin();
        login(appLogin.getLoginObject());
    }
    
    @Override
    public void notifyUsers (List<IUser> users, String message) {
        FacebookNotifyUsers notify = new FacebookNotifyUsers();
        notify.createNotification(users, message);
        notify.send(getLogin().getService(), getLogin().getToken());
    }

    @Override
    public void customPost (String message, SocialProfile profile) {
        FacebookCustomPost post = new FacebookCustomPost();
        post.createPost(message, profile);
        post.send(getLogin().getService(), getLogin().getToken());
    }

    @Override
    public void HighScoreBoardPost (HighScoreBoard board, SocialProfile profile) {
        // TODO Auto-generated method stub
        
    }

}
