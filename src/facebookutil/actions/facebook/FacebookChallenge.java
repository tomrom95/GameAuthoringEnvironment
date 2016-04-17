package facebookutil.actions.facebook;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.Challenge;
import facebookutil.user.IUser;

public class FacebookChallenge implements Challenge{

    @Override
    public void createChallenge (IUser source, IUser target, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        // TODO Auto-generated method stub
        
    }

}
