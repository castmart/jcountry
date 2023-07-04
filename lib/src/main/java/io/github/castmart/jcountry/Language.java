package io.github.castmart.jcountry;

/**
 * Model Class to store Country Information. ISO-639-2
 * {
 *       "alpha_2": "es",
 *       "alpha_3": "spa",
 *       "name": "Spanish; Castilian"
 *     }
 */
public class Language {
    private final String alpha2;
    private final String alpha3;
    private final String name;

    protected Language(String alpha2, String alpha3, String name) {
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.name = name;
    }


    public String getAlpha2() {
        return alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public String getName() {
        return name;
    }
}
