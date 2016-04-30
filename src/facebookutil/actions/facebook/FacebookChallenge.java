package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import facebookutil.actions.Challenge;
import facebookutil.actions.OAuthAction;
import facebookutil.applications.App;
import facebookutil.user.profiles.UserProfile;
import gameauthoring.util.ErrorMessage;

public class FacebookChallenge extends OAuthAction implements Challenge{
    private static final String CHALLENGE_URL = "https://graph.facebook.com/%s/apprequests";
    private static final String ERROR = "Target is not on facebook";

    @Override
    public void createChallenge (App application, UserProfile target, String message) {
        if (target == null) {
            new ErrorMessage(ERROR).showError();
            return;
        }
        OAuthRequest myRequest = new OAuthRequest(Verb.POST,
                                 String.format(CHALLENGE_URL, target.getID()),
                                 application.getLogin().getService());
        myRequest.addBodyParameter("message", message);
        setRequest(myRequest);
    }

}
