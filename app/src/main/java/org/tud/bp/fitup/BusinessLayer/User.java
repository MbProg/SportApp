package org.tud.bp.fitup.BusinessLayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.tud.bp.fitup.DataAccessLayer.DAL_RegisteredUsers;
import org.tud.bp.fitup.DataAccessLayer.DAL_User;

/**
 * Created by M.Braei, Y.Aziani, Felix, Basti on 31.01.2017.
 */

public class User {

    private String name;
    private int points;
    private Challenge challenge = null;
    private String email;
    private String challangeName;
    private Context context;


    public static User Create(String Name) {
        User user = new User(Name);
        return user;
    }

    static public User createUser(String username, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        User mainUser = User.Create(username);
        preferences.edit().putString("logedIn", username).commit();
        return mainUser;
    }

    private User(String Name) {
        name = Name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChallangeName(String challangeName) {
        this.challangeName = challangeName;
    }

    public String getChallangeName() {
        return challangeName;
    }

    public String getEmail() {
        return this.email;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean InsertStimmung(StimmungsAngabe stimmungsAngabe, Context context) {
        DAL_User.InsertStimmung(this, stimmungsAngabe, context);
        return true;
    }

    public boolean UpdateStimmung(StimmungsAngabe stimmungsAngabe, Context context) {
        DAL_User.UpdateStimmung(this, stimmungsAngabe, context);
        return true;
    }

    public boolean InsertStimmungScore(StimmungAbfrageScore stimmungAbfrageScore, Context context) {
        DAL_User.InsertStimmungScore(this, stimmungAbfrageScore, context);
        return true;
    }

    public boolean UpdateStimmungScore(StimmungAbfrageScore stimmungAbfrageScore, Context context) {
        DAL_User.UpdateStimmungScore(this, stimmungAbfrageScore, context);
        return true;
    }


    public boolean InsertFitnessFragebogen(FitnessFragebogen fitnessfragebogen, Context context) {
        DAL_User.InsertFitnessFragebogen(this, fitnessfragebogen, context);
        return true;
    }

    public boolean UpdateFitnessFragebogen(FitnessFragebogen fitnessfragebogen, Context context) {
        DAL_User.UpdateFitnessFragebogen(this, fitnessfragebogen, context);
        return true;
    }

    public boolean InsertFragebogen(Fragebogen fragebogen, Context context) {
        DAL_User.InsertFragebogen(this, fragebogen, context);
        return true;
    }

    public boolean UpdateFragebogen(Fragebogen fragebogen, Context context) {
        DAL_User.UpdateFragebogen(this, fragebogen, context);
        return true;
    }

    public boolean SaveDiaryEntry(DiaryEntry diaryEntry, Context context) {
        DAL_User.InsertDiaryEntry(this, diaryEntry, context);
        return true;
    }


    /**
     * challenge to DAL_User to save its into database
     *
     * @param challenge challenge to add
     * @return
     */
    public boolean InsertChallenge(Challenge challenge, Context context) {
        DAL_User.InsertChallenge(this, challenge, context);
        return true;
    }

    public void saveRegistration(String username, String password, Context context) {
        DAL_RegisteredUsers.insertRegistration(username, password, context);
    }
}
