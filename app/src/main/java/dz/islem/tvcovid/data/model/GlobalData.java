package dz.islem.tvcovid.data.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GlobalData implements Serializable {
    private final String title = "Global Data";
    private final String description = "Click for more info";
    private final String thumbnail ="global_icon";

    @Expose
    @SerializedName("cases")
    private long nbrCases;

    @Expose
    @SerializedName("todayCases")
    private long todayCases;

    @Expose
    @SerializedName("deaths")
    private long nbrDeaths;

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
    @SerializedName("updated")
    private long updated;

    public GlobalData(long nbrCases, long nbrDeaths, long nbrRecovered, long updated) {
        this.nbrCases = nbrCases;
        this.nbrDeaths = nbrDeaths;
        this.nbrRecovered = nbrRecovered;
        this.updated = updated;
    }

    public long getNbrCases() {
        return nbrCases;
    }

    public void setNbrCases(long nbrCases) {
        this.nbrCases = nbrCases;
    }

    public long getNbrDeaths() {
        return nbrDeaths;
    }

    public void setNbrDeaths(long nbrDeaths) {
        this.nbrDeaths = nbrDeaths;
    }

    public long getNbrRecovered() {
        return nbrRecovered;
    }

    public void setNbrRecovered(long nbrRecovered) {
        this.nbrRecovered = nbrRecovered;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long todayCases) {
        this.todayCases = todayCases;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(long todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public int getImageResource(Context context) {
        return context.getResources()
                .getIdentifier(thumbnail, "drawable", context.getPackageName());
    }
}
