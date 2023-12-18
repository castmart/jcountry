[![CI](https://github.com/castmart/jcountry/actions/workflows/ci.yaml/badge.svg)](https://github.com/castmart/jcountry/actions/workflows/ci.yaml)

# JCountry

This project tries to replicate the same functionality as [pycountry](https://github.com/flyingcircusio/pycountry) by wrapping iso files and provide a programatic interface.

This will help having a quick source for translations of country names, and the extra information provided by:
- ISO 3166-1 (Countries)
- ISO 639-2 (Languages)

For the status of the translations, please check the [iso codes](https://github.com/sailfishos-mirror/iso-codes) repository (which is the original source of the iso files and translations).

## Dependency
```java
// Maven
<dependency>
    <groupId>io.github.castmart</groupId>
    <artifactId>jcountry</artifactId>
    <version>0.0.2</version>
</dependency>
```
```kotlin
// Gradle Kotlin
implementation("io.github.castmart:jcountry:0.0.2")
```
```java
// Gradle short
implementation 'io.github.castmart:jcountry:0.0.2'
```
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

    // Languages DB
    LanguageDB languageDB = new LanguageDBImpl(true);
    var dbByAlpha2 = languageDB.getLanguagesMapByAlpha2();

    // Get Translations by language based locale 
    Optional<ResourceBundle> bundle = languageDB.getLanguagesTranslations(Locale.GERMAN);
    
    // Spanisch (Kastilisch)
    var translatedCountryName = bundle.get().getString(dbByAlpha2.get("es").getName());
```