package facebookutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHelper {
    
    private static final String JSON_REGEX = "\"%s\":\"([^&]+?)\"";
    
    public static String getFirstGroup (String pattern, String body) {
        Matcher m = Pattern.compile(pattern).matcher(body);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
    
    public static String JSONParse (String id, String body) {
        String regex = String.format(JSON_REGEX, id);
        return getFirstGroup(regex, body);
    }

}
