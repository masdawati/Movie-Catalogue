package id.divascion.moviecatalogue.model.credits;

import org.json.JSONException;
import org.json.JSONObject;

public class CastOfTv {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public CastOfTv(String name, String character, String profilePath) {
        this.name = name;
        this.character = character;
        this.profilePath = profilePath;
    }

    public CastOfTv(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.character = object.getString("character");
            this.profilePath = object.getString("profile_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CastOfTv() {
    }

    private String name;
    private String character;
    private String profilePath;
}
