package com.castmart.jcountry;

import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public interface CountryDB {
    HashMap<String, Country> getCountriesMapByAlpha2();
    HashMap<String, Country> getCountriesMapByAlpha3();
    HashMap<String, Country> getCountriesMapByName();
    Optional<ResourceBundle> getCountriesTranslations(Locale languageLocale);
}
