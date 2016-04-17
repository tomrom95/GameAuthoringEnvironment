package facebookutil.actions;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * This interface allows the user to utilize social action methods
 *
 */
public interface SocialAction {
    
    public void send (OAuth20Service service, OAuth2AccessToken token);

}
