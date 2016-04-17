package facebookutil.user;

import facebookutil.SocialType;
import facebookutil.login.Login;

public class User implements IUser{
    
    private String myEmail;
    private UserScoreBoard myScoreBoard;
    private SocialMap myProfiles;
    
    public User (String email) {
        myEmail = email;
        myScoreBoard = new UserScoreBoard();
        myProfiles = new SocialMap ();
    }

    @Override
    public String getUserEmail () {
        return myEmail;
    }

    @Override
    public UserScoreBoard getScoreBoard () {
        return myScoreBoard;
    }

    @Override
    public void logout () {
        // TODO setup with XStream
        
    }
    
    @Override
    public void login (SocialType type, Login login) {
        if (myProfiles.getProfileByType(type) == null){
            createNewProfile(type, login);
        } else {
            loginExisting(type, login);
        }
    }
    
    private void loginExisting (SocialType type, Login login) {
        myProfiles.getProfileByType(type).login(login);
        myProfiles.setActive(type);
    }

    private void createNewProfile (SocialType type, Login login) {
        myProfiles.createNewProfile(type, login.getUserID());
        loginExisting(type, login);
    }

    @Override
    public SocialMap getProfiles () {
        return myProfiles;
    }

}
