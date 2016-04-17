package facebookutil.login;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

public class LoginObject {
    
    private OAuth2AccessToken myToken;
    private OAuth20Service myService;
    private String myEmail;
    private String myUserID;
    
    public OAuth2AccessToken getToken (){
        return myToken;
    }
    
    public OAuth20Service getService () {
        return myService;
    }
    
    public String getEmail () {
        return myEmail;
    }
    
    public String getUserID () {
        return myUserID;
    }
    
    public void setToken (OAuth2AccessToken token){
        myToken = token;
    }
    
    public void setService (OAuth20Service service) {
        myService = service;
    }
    
    public void setEmail (String email) {
        myEmail = email;
    }
    
    public void setUserID (String userID) {
        myUserID = userID;
    }

}
