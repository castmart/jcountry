package com.jimdo.jcountry;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * LanguageDB defines the API to interact with the Language DB which is based on ISO-639-2.
 */
public interface LanguageDB {

    /**
     * Returns a languages hash map with the alpha code of two characters as object key. [String, Language]
     * @return A languages hash map with the alpha code of two characters as object key. [String, Language]
     */
    HashMap<String, Language> getLanguagesMapByAlpha2();

    /**
     * Returns a languages hash map with the alpha code of three characters as object key. [String, Language]
     * @return A languages hash map with the alpha code of three characters as object key. [String, Language]
     */
    HashMap<String, Language> getLanguagesMapByAlpha3();

    /**
     * Return a languages hash map with the language name as object key. [String, Language]
     * @return A languages hash map with the language name as object key. [String, Language]
     */
    HashMap<String, Language> getLanguagesMapByName();

    /**
     * Returns a ResourceBundle with the language name translations
     * @param languageLocale a language locale indicating the desire language translations to be loaded.
     * @return a ResourceBundle with the language name translations
     */
    Optional<ResourceBundle> getLanguagesTranslations(Locale languageLocale);
}
