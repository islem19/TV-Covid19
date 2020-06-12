package dz.islem.tvcovid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryData implements Serializable {
    private final String description = "Click for more info";

    @Expose
    @SerializedName("country")
    private String country;

    @Expose
    @SerializedName("countryInfo")
    private CountryInfo countryInfo;

    @Expose
    @SerializedName("cases")
    private long nbrCases;

    @Expose
    @SerializedName("todayCases")
    private long todayCases;

    @Expose
    @SerializedName("deaths")
    private long nbrDeath;

    @Expose
    @SerializedName("todayDeaths")
    private long todayDeaths;

    @Expose
    @SerializedName("recovered")
    private long nbrRecovered;

    @Expose
    @SerializedName("todayRecovered")
    private long todayRecovered;

    @Expose
    @SerializedName("active")
    private long nbrActiveCases;

    @Expose
    @SerializedName("critical")
    private long nbrCriticalCases;



    public CountryData(String country, long nbrCases,
                            long todayCases, long nbrDeath,
                            long todayDeaths, long nbrRecovered, long todayRecovered,
                            long nbrActiveCases, long nbrCriticalCases,
                            long nbrCasesPerOneMillion) {
        this.country = country;
        this.nbrCases = nbrCases;
        this.todayCases = todayCases;
        this.nbrDeath = nbrDeath;
        this.todayDeaths = todayDeaths;
        this.nbrRecovered = nbrRecovered;
        this.todayRecovered = todayRecovered;
        this.nbrActiveCases = nbrActiveCases;
        this.nbrCriticalCases = nbrCriticalCases;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getNbrCases() {
        return nbrCases;
    }

    public void setNbrCases(long nbrCases) {
        this.nbrCases = nbrCases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long todayCases) {
        this.todayCases = todayCases;
    }

    public long getNbrDeath() {
        return nbrDeath;
    }

    public void setNbrDeath(long nbrDeath) {
        this.nbrDeath = nbrDeath;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getNbrRecovered() {
        return nbrRecovered;
    }

    public void setNbrRecovered(long nbrRecovered) {
        this.nbrRecovered = nbrRecovered;
    }

    public long getNbrActiveCases() {
        return nbrActiveCases;
    }

    public void setNbrActiveCases(long nbrActiveCases) {
        this.nbrActiveCases = nbrActiveCases;
    }

    public long getNbrCriticalCases() {
        return nbrCriticalCases;
    }

    public void setNbrCriticalCases(long nbrCriticalCases) {
        this.nbrCriticalCases = nbrCriticalCases;
    }

    public String getDescription() {
        return description;
    }

    public long getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(long todayRecovered) {
        this.todayRecovered = todayRecovered;
    }
}
