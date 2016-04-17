package facebookutil.actions.facebook;

import java.util.List;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import facebookutil.actions.NotifyUsers;
import facebookutil.user.IUser;

public class FacebookNotifyUsers implements NotifyUsers {

    @Override
    public void send (OAuth20Service service, OAuth2AccessToken token) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createNotification (List<IUser> users, String message) {
        // TODO Auto-generated method stub
        
    }

}
