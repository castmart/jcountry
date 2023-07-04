package io.github.castmart.jcountry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.*;

public class LanguageDBImpl implements LanguageDB {

    private static final  String bundleName = "iso_639.iso_639-2";
    private static final String databaseFile = "639-2.json";
    private static final HashMap<Locale, ResourceBundle> loadedBundles = new HashMap<>();

    private static final HashMap<String, Language> languageMapByAlpha2 = new HashMap<>();

    private static final HashMap<String, Language> languageMapByAlpha3 = new HashMap<>();

    private static final HashMap<String, Language> languageMapByName = new HashMap<>();

    protected LanguageDBImpl() { generateLanguageDB(false); }

    protected LanguageDBImpl(boolean isInternalTest) { generateLanguageDB(isInternalTest); }

    @Override
    public HashMap<String, Language> getLanguagesMapByAlpha2() {
        return languageMapByAlpha2;
    }

    @Override
    public HashMap<String, Language> getLanguagesMapByAlpha3() {
        return languageMapByAlpha3;
    }

    @Override
    public HashMap<String, Language> getLanguagesMapByName() {
        return languageMapByName;
    }

    private void generateLanguageDB(boolean isInternalTest) {
        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(isInternalTest ? "./"+databaseFile : databaseFile);
            if (inputStream == null) {
                throw new NullPointerException("Cannot find resource file " + databaseFile);
            }
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONObject object = new JSONObject(tokener);
            JSONArray languagesObject = object.getJSONArray("639-2");
            for (int index = 0; index < languagesObject.length(); index++) {
                Language language = readLanguage((JSONObject)languagesObject.get(index));
                languageMapByAlpha2.put(language.getAlpha2(), language);
                languageMapByAlpha3.put(language.getAlpha3(), language);
                languageMapByName.put(language.getName(), language);
            }
        } catch(NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    private Language readLanguage(JSONObject languageObject) {
        return new Language(
                languageObject.optString("alpha_2"),
                languageObject.optString("alpha_3"),
                languageObject.optString("name")
        );
    }

    @Override
    public Optional<ResourceBundle> getLanguagesTranslations(Locale languageLocale) {
        try {
            // Optimize the bundle loading/reading to be loaded once per Locale.
            if (loadedBundles.containsKey(languageLocale)) {
                return Optional.of(loadedBundles.get(languageLocale));
            }
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, languageLocale);
            loadedBundles.put(languageLocale, bundle);
            return Optional.of(bundle);
        } catch (MissingResourceException exception) {
            return Optional.empty();
        }
    }
}
