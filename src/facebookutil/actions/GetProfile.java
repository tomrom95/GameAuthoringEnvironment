package facebookutil.actions;

import facebookutil.login.LoginObject;

public interface GetProfile extends SocialAction {
    
    public void createGet (LoginObject login);
    
    public String getEmail ();
    
    public String getUserID ();

}
