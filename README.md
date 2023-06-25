# JCountry

This project tries to replicate the same functionality as [pycountry](https://github.com/flyingcircusio/pycountry) by wrapping iso files and provide a programatic interface.

This will help having a quick source for translations of country names, and the extra information provided by:
- ISO 3166-1 (Countries)
- ISO 639-2 (Languages)

For the status of the translations, please check the [iso codes](https://github.com/sailfishos-mirror/iso-codes) repository (which is the original source of the iso files and translations).

## How to use it?

```java
    JCountry jcountry = new JCountry();
    CountryDB countryDB = jcountry.getCountriesDB();
    // Get the countries DB hash maps <String, Country>
    var dbByAlpha2 = countryDB.getCountriesMapByAlpha2();
    var dbByAlpha3 = countryDB.getCountriesMapByAlpha3();
    var dbByName = countryDB.getCountriesMapByName();
    
    // Get Translations by language based locale 
    Optional<ResourceBundle> bundle = countryDB.getCountriesTranslations(Locale.GERMAN);
    
    // MX -> Mexiko
    var translatedCountryName = bundle.get().getString(dbByAlpha2.get("MX").getName());
```