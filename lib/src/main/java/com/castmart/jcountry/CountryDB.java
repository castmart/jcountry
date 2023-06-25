package com.castmart.jcountry;

import java.util.ArrayList;
import java.util.Locale;

public interface CountryDB {
    ArrayList<Country> getCountries(Locale Locale);
}
