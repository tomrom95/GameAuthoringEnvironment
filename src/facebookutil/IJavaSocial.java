package facebookutil;

import java.util.Set;
import facebookutil.login.Login;
import facebookutil.user.IUser;

public interface IJavaSocial {
    
    public Set<IUser> getUsers ();
    
    public IUser getUserByEmail (String email);
    
    public HighScoreBoard getHighScoreBoard ();
    
    public void loginUser (String email, SocialType type, Login login);
    
    public IUser createNewUser (String email);

}
