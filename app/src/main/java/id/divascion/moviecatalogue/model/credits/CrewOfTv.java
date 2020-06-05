package id.divascion.moviecatalogue.model.credits;

import org.json.JSONException;
import org.json.JSONObject;

public class CrewOfTv {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public CrewOfTv(String name, String job, String profilePath) {
        this.name = name;
        this.job = job;
        this.profilePath = profilePath;
    }

    public CrewOfTv(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.job = object.getString("job");
            this.profilePath = object.getString("profile_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CrewOfTv() {
    }

    private String name;
    private String job;
    private String profilePath;
}
