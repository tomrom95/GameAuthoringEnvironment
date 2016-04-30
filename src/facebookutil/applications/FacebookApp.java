package facebookutil.applications;

import java.util.List;
import facebookutil.SocialType;
import facebookutil.actions.facebook.FacebookChallenge;
import facebookutil.actions.facebook.FacebookNotifyUsers;
import facebookutil.user.IUser;

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
        notify.send(getLogin());
    }


    @Override
    public void challenge (IUser target, String message) {
        FacebookChallenge challenge = new FacebookChallenge();
        challenge.createChallenge(this,
                                  target.getProfiles().getProfileByType(SocialType.FACEBOOK), message);
        challenge.send(getLogin());
    }
    

}
