package io.github.castmart.jcountry;

import java.io.InputStream;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * CountryDBImpl is a class implementation of CountryDB that uses org.JSON library to load the OSI-3166-1 countries
 * information. This class also loads the Countries translations as Resource bundle objects, and to avoid loading such
 * elements, it stores them in a hash map for future access.
 *
 */
class CountryDBImpl implements CountryDB {
    private static final  String bundleName = "iso_3166.iso_3166-1";
    private static final  String databaseFile = "3166-1.json";
    private static final HashMap<Locale, ResourceBundle> loadedBundles = new HashMap<>();

    private static final HashMap<String, Country> countryMapByAlpha2 = new HashMap<>();

    private static final HashMap<String, Country> countryMapByAlpha3 = new HashMap<>();

    private static final HashMap<String, Country> countryMapByName = new HashMap<>();
    protected CountryDBImpl() {
        generateDatabase(false);
    }

    protected CountryDBImpl(boolean isInternalTest) {
        generateDatabase(isInternalTest);
    }

    @Override
    public HashMap<String, Country> getCountriesMapByAlpha2() {
        return countryMapByAlpha2;
    }

    @Override
    public HashMap<String, Country> getCountriesMapByAlpha3() {
        return countryMapByAlpha3;
    }

    @Override
    public HashMap<String, Country> getCountriesMapByName() {
        return countryMapByName;
    }

    @Override
    public Optional<ResourceBundle> getCountriesTranslations(Locale languageLocale) {
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


    private void generateDatabase(boolean isInternalTest) {
        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(isInternalTest ? "./"+databaseFile : databaseFile);
            if (inputStream == null) {
                throw new NullPointerException("Cannot find resource file " + databaseFile);
            }
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONObject object = new JSONObject(tokener);
            JSONArray countriesObject = object.getJSONArray("3166-1");
            for (int index = 0 ; index < countriesObject.length(); index++) {
                Country country = readCountry((JSONObject)countriesObject.get(index));
                countryMapByAlpha2.put(country.getAlpha2(), country);
                countryMapByAlpha3.put(country.getAlpha3(), country);
                countryMapByName.put(country.getName(), country);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Country readCountry(JSONObject countryObject) {
        return new Country(
                countryObject.getString("alpha_2"),
                countryObject.getString("alpha_3"),
                countryObject.getString("flag"),
                countryObject.getString("name"),
                countryObject.getString("numeric")
        );
    }

}
