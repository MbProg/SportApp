package org.tud.bp.fitup.DataAccessLayer;

import android.app.Dialog;
import android.content.Context;

import org.tud.bp.fitup.Activity.ActivityLogin;
import org.tud.bp.fitup.Activity.ActivityNewChallenge;
import org.tud.bp.fitup.Activity.ActivityChallenge;
import org.tud.bp.fitup.BusinessLayer.RegisterCatcher;
import org.tud.bp.fitup.BusinessLayer.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.URL;

/**
 * class to access registered users
 * Created by Aziani on 23.03.2017.
 */

public class DAL_RegisteredUsers {

    /**
     * get registered users and hand it to the activity
     *
     * @param activity the displaying activity
     */
    public static void getRegisteredUsers(
            final ActivityLogin activity) {
        // access data in database and hand it to activity
        try {
            URL url = new URL(DAL_Utilities.getDatabaseURL(activity) + "users");
            Firebase root = new Firebase(url.toString());
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    activity.returnRegisteredUsers(dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * get registered users and hand it to the activity with dialog and usermail
     *
     * @param activity the displaying activity
     * @param searchedUser the searched mail address of the user
     * @param dialog dialog where the mail address has been entered
     *
     */
    public static void checkRegisteredUsersNewChallenge(
            final ActivityNewChallenge activity, final String searchedUser, final Dialog dialog) {
        // access data in database and hand it to activity
        try {
            URL url = new URL(DAL_Utilities.getDatabaseURL(activity) + "users");
            Firebase root = new Firebase(url.toString());
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    activity.returnRegisteredUsers(dataSnapshot, searchedUser, dialog);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * get registered users and hand it to the activity
     *
     * @param activity the displaying activity
     */
    public static void checkRegisteredUsersChallenge(
            final ActivityChallenge activity, final String searchedUser, final Dialog dialog) {
        // access data in database and hand it to activity
        try {
            URL url = new URL(DAL_Utilities.getDatabaseURL(activity) + "users");
            Firebase root = new Firebase(url.toString());
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    activity.returnRegisteredUsers(dataSnapshot, searchedUser, dialog);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertRegistration(String username, String password, Context context) {
        try {
            // setting up url for the database
            URL url = new URL(DAL_Utilities.getDatabaseURL(context) + "users");
            Firebase root = new Firebase(url.toString());
            // insert user
            root.child(username).child("password").setValue(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadRegistration(final RegisterCatcher registerCatcher, final Context context) {
        try {
            URL url = new URL(DAL_Utilities.getKompassURL(context) +  "users");
            Firebase root = new Firebase(url.toString());
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    registerCatcher.returnRegistrations(dataSnapshot, context);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    registerCatcher.returnRegistrations(null, context);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertMail(String mail, User user, Context context) {
        try {
            URL url = new URL(DAL_Utilities.getKompassURL(context)+ "users/" + user.getName());
            Firebase root = new Firebase(url.toString());
            // insert user
            root.child("email").setValue(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
