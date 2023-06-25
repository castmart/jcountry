/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.castmart.jcountry;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class JCountryTest {
    @Test
    void testJCountryReturnsCountryDB() {
        JCountry jcountry = new JCountry();
        CountryDB countryDB = jcountry.getCountriesDB();
        assertNotNull(countryDB, "getCountriesDB should return an instance of the countries DB");
    }

    @Test
    void testJCountryDBReadsJson() {
        JCountry jcountry = new JCountry();
        CountryDB countryDB = jcountry.getCountriesDB();
        assertNotNull(countryDB, "getCountriesDB should return an instance of the countries DB");

        var dbByAlpha2 = countryDB.getCountriesMapByAlpha2();
        var dbByAlpha3 = countryDB.getCountriesMapByAlpha3();
        var dbByName = countryDB.getCountriesMapByName();
        assertNotNull(dbByAlpha2);
        assertNotNull(dbByAlpha3);
        assertNotNull(dbByName);

        assertTrue(!dbByAlpha2.isEmpty());
        assertTrue(!dbByAlpha3.isEmpty());
        assertTrue(!dbByName.isEmpty());

        assertEquals(dbByName.size(), dbByAlpha2.size());
        assertEquals(dbByAlpha2.size(), dbByAlpha3.size());
    }

    @Test
    void testJCountryReadTranslations() {
        JCountry jcountry = new JCountry();
        CountryDB countryDB = jcountry.getCountriesDB();

        Optional<ResourceBundle> bundle = countryDB.getCountriesTranslations(Locale.GERMAN);
        assertTrue(bundle.isPresent());

        Optional<ResourceBundle> bundleTwo = countryDB.getCountriesTranslations(new Locale("Unexistent Lang"));
        assertFalse(bundleTwo.isPresent());
    }
}
