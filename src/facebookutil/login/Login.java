package facebookutil.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

public interface Login {
    
    public void createToken ();
    
    public OAuth2AccessToken getToken ();
    
    public OAuth20Service getService ();

}
