package io.github.castmart.jcountry;

public class Country {

    private String alpha2;
    private String alpha3;
    private String flag;
    private String name;
    private String numeric;

    public Country(String alpha2, String alpha3, String flag, String name, String numeric) {
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.flag = flag;
        this.name = name;
        this.numeric = numeric;
    }

    public String getAlpha2() { return alpha2; }
    public String getAlpha3() { return alpha3; }
    public String getFlag() { return flag; }
    public String getName() { return name; }
    public String getNumeric() { return numeric; }

}
