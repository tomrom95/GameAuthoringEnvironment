package facebookutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHelper {
    
    public static String getFirstGroup (String pattern, String body) {
        Matcher m = Pattern.compile(pattern).matcher(body);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

}
