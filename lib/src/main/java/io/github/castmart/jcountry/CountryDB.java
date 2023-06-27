package io.github.castmart.jcountry;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * CountryDB defines the API to interact with the country DB which is based on ISO-3166-1.
 */
public interface CountryDB {
    /**
     * Return a countries hash map with the alpha code of two characters as object key. [String, Country]
     * @return A countries hash map with the alpha code of two characters as object key.
     */
    HashMap<String, Country> getCountriesMapByAlpha2();
    /**
     * Return a countries hash map with the alpha code of three characters as object key. [String, Country]
     * @return A countries hash map with the alpha code of three characters as object key.
     */
    HashMap<String, Country> getCountriesMapByAlpha3();
    /**
     * Return a countries hash map with the country name as object key. [String, Country]
     * @return A countries hash map with the country name as object key.
     */
    HashMap<String, Country> getCountriesMapByName();

    /**
     * Returns a ResourceBundle with the country name translations
     * @param languageLocale Locale indicating the translation's language to be loaded.
     * @return Returns a ResourceBundle with the country name translations.
     */
    Optional<ResourceBundle> getCountriesTranslations(Locale languageLocale);
}
