package com.castmart.jcountry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

class CountryDBImpl implements CountryDB {
    private static final  String bundleName = "iso_3166.iso_3166-1";
    private static final  String databaseFile = "./3166-1.json";

    @Override
    public ArrayList<Country> getCountries(Locale languageLocale) {
        try {
            // Get translations based on language
            var translations = ResourceBundle.getBundle(bundleName,languageLocale);
            return loadCountriesFromJSON(translations);
        } catch(MissingResourceException exception) {
            // If translations are not found then return the default info in english
            return loadCountriesFromJSON(null);
        } 
        
    } 

    private ArrayList<Country> loadCountriesFromJSON(ResourceBundle translations) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(databaseFile);
            System.out.println("<<< " + CountryDBImpl.class.getResource("/").getPath());
            if (inputStream == null) {
                throw new NullPointerException("Cannot find resource file " + databaseFile);
            }
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONObject object = new JSONObject(tokener);
            JSONArray countriesObject = object.getJSONArray("3166-1");
            for (int index = 0 ; index < countriesObject.length(); index++) {

                countries.add(readCountry((JSONObject)countriesObject.get(index), translations));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private Country readCountry(JSONObject countryObject, ResourceBundle translations) {
        String alpha2 = countryObject.getString("alpha_2");
        String alpha3 = countryObject.getString("alpha_3");
        String flag = countryObject.getString("flag");
        String numeric = countryObject.getString("numeric");
        String name = null;
        try {
            name = translations != null? translations.getString(countryObject.getString("name")) : countryObject.getString("name");
        } catch (NullPointerException | MissingResourceException | ClassCastException exception) {
            // No translation for the country, then default to english
            name = countryObject.getString("name");
            exception.printStackTrace();
        }
        
        return new Country(alpha2, alpha3, flag, name, numeric); 
    }

}
