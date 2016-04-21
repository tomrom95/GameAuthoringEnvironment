package splash;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class LocaleManager {

    private static LocaleManager ourInstance;
    private final ResourceBundle myLanguages =
            ResourceBundle.getBundle("defaults/supportedLanguages");
    private ObjectProperty<Locale> myCurrentLocale;

    private LocaleManager () {
        myCurrentLocale = new SimpleObjectProperty<>(Locale.getDefault());
    }

    public static LocaleManager getInstance () {
        if (ourInstance == null) {
            ourInstance = new LocaleManager();
        }
        return ourInstance;
    }

    public ObjectProperty<Locale> getCurrentLocaleProperty () {
        return myCurrentLocale;
    }

    public void setCurrentLocale (Locale locale) {
        myCurrentLocale.set(locale);
    }

    public List<Locale> getSupportedLocales () {
        return Collections.list(myLanguages.getKeys()).stream()
                .map(key -> new Locale(key))
                .collect(Collectors.toList());
    }

}
