package facebookutil.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * This interface provides the methods handle logins with users on various platforms
 *
 */
public interface Login {
    
    public void createToken ();
    
    public OAuth2AccessToken getToken ();
    
    public OAuth20Service getService ();

}
