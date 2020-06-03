package id.divascion.moviecatalogue.presenter;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class Tv implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String language;
    private double rating;
    private String poster;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Tv(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.description = object.getString("overview");
            this.language = object.getString("original_language");
            this.rating = object.getDouble("vote_average");
            this.date = object.getString("first_air_date");
            this.poster = object.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Tv() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Tv(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.poster = in.readString();
        this.language = in.readString();
        this.date = in.readString();
        this.rating = in.readDouble();
    }

    public static final Creator<Tv> CREATOR = new Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel source) {
            return new Tv(source);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.poster);
        dest.writeString(this.language);
        dest.writeString(this.date);
        dest.writeDouble(this.rating);

    }
}

