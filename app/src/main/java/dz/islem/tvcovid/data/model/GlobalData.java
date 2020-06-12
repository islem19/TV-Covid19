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
    @SerializedName("deaths")
    private long nbrDeaths;

    @Expose
    @SerializedName("recovered")
    private long nbrRecovered;

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

    public int getImageResource(Context context) {
        return context.getResources()
                .getIdentifier(thumbnail, "drawable", context.getPackageName());
    }
}
