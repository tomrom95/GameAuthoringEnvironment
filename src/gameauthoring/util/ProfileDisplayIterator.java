package gameauthoring.util;

import java.util.List;
import engine.profile.ProfileDisplay;


/**
 * Helper class to match a string to its associated Profile Display
 *
 * @author Joe Lilien
 *
 */
public class ProfileDisplayIterator {

    public ProfileDisplay matchStringtoProfile (List<ProfileDisplay> pds, String effectType) {
        for (ProfileDisplay pd : pds) {
            if (pd.getProfile().getName().get().equals(effectType)) {
                return pd;
            }
        }
        return null;
    }
}
