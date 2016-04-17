package facebookutil;

import java.lang.reflect.InvocationTargetException;
import facebookutil.login.FacebookLogin;
import facebookutil.login.LocalLogin;
import facebookutil.login.Login;
import facebookutil.login.TwitterLogin;
import facebookutil.user.FacebookProfile;
import facebookutil.user.LocalProfile;
import facebookutil.user.SocialProfile;
import facebookutil.user.TwitterProfile;

public enum SocialType {
    Facebook (FacebookLogin.class, FacebookProfile.class),
    Twitter (TwitterLogin.class, TwitterProfile.class),
    Local (LocalLogin.class, LocalProfile.class);
    
    private Class<? extends Login> myLogin;
    private Class<? extends SocialProfile> myProfile;
    
    SocialType (Class<? extends Login> loginClass, Class<? extends SocialProfile> profileClass) {
        myLogin = loginClass;
        myProfile = profileClass;
    }
    
    public Login getLogin () {
        try {
            return myLogin.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
    
    public SocialProfile getProfile (String userID) {
        try {
            return myProfile.getDeclaredConstructor(String.class).newInstance(userID);
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            return null;
        }
    }

}
