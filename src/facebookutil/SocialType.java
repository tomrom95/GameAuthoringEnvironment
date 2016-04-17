package facebookutil;

import facebookutil.login.FacebookLogin;
import facebookutil.login.LocalLogin;
import facebookutil.login.Login;
import facebookutil.login.TwitterLogin;

public enum SocialType {
    Facebook (FacebookLogin.class),
    Twitter (TwitterLogin.class),
    Local (LocalLogin.class);
    
    private Class<? extends Login> myLogin;
    
    SocialType (Class<? extends Login> loginClass) {
        myLogin = loginClass;
    }
    
    public Login getLogin() {
        try {
            return myLogin.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

}
