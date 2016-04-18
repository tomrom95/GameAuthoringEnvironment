package facebookutil;

import java.util.List;
import facebookutil.login.LoginObject;
import facebookutil.user.IUser;

public interface IJavaSocial {
    
    public List<IUser> getUsers ();
    
    public IUser getUserByEmail (String email);
    
    public HighScoreBoard getHighScoreBoard ();
    
    public void loginUser (SocialType type, LoginObject login);
    
    public void loginUser(SocialType type);
    
    public IUser createNewUser (String email);

}
