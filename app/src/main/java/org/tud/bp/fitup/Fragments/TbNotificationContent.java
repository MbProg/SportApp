package org.tud.bp.fitup.Fragments;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tud.bp.fitup.Activity.ActivityMain;
import org.tud.bp.fitup.Adapters.NotificationAdapter;
import org.tud.bp.fitup.BusinessLayer.Notification;
import org.tud.bp.fitup.DataAccessLayer.DAL_Utilities;
import org.tud.bp.fitup.Observe.Observer;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by M.Braei on 07.01.2017.
 */

public class TbNotificationContent extends TabFragment {

    List<Notification> notifications;
    RecyclerView rv;
    View view;
    ProgressDialog pd;

    TbNotificationContent tbNotificationContent = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setTitle(getString(org.tud.bp.fitup.R.string.notifikationen));


        if (isAdded()) {
            view = inflater.inflate(org.tud.bp.fitup.R.layout.tbnotificationcontent, container, false);
        }

        tbNotificationContent = this;
        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {
            pd = new ProgressDialog(this.getContext());
            pd.setMessage(getString(org.tud.bp.fitup.R.string.wird_geladen));
            pd.show();

            readNotifications();

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    void readNotifications() {

        if (!isAdded()) {
            return;
        }

        try {
            URL url = new URL(DAL_Utilities.getDatabaseURL(this.getContext()) + "users/" + ActivityMain.getMainUser(this
                    .getContext())
                    .getName() + "/Notifications/");
            final Firebase root = new Firebase(url.toString());

            root.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (!TbNotificationContent.this.isAdded()) {
                        return;
                    }

                    // dataSnapshot.getKey() declares which strategy the notification belongs to (Stimmungsabgabe....)
                    notifications = new LinkedList<>();
                    // the child.key of dataSnapshop declare the unique datetime of the notification
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String title = child.getKey();
                        for (DataSnapshot dates : child.getChildren()) {
                            Date date = DAL_Utilities.ConvertFirebaseStringToDateTime(dates.getKey());

                            if (dates.child("subText").getValue() == null) {
                                continue;
                            }

                            // Now we can read for each unique datetime the text
                            String subtext = dates.child("subText").getValue().toString();

                            Notification notification = null;
                            // Now create our Notifications
                            if (title.equals(getString(org.tud.bp.fitup.R.string.stimmungsabgabe))) {
                                notification = new Notification(title, subtext, org.tud.bp.fitup.R.mipmap.ic_stimmungs_abgabe, date);
                            } else if (title.equals(getString(org.tud.bp.fitup.R.string.tagebucheintrag))) {
                                notification = new Notification(title, subtext, org.tud.bp.fitup.R.mipmap.ic_tagebuch_eintrag, date);
                            } else if (title.equals(getString(org.tud.bp.fitup.R.string.fitnessfragebogen))) {
                                notification = new Notification(title, subtext, org.tud.bp.fitup.R.mipmap.ic_aktivitaets_fragebogen,
                                        date);
                            } else if (title.equals(getString(org.tud.bp.fitup.R.string.aktivitaetsfragebogen))) {
                                notification = new Notification(title, subtext, org.tud.bp.fitup.R.mipmap.ic_fittnessfragebogen, date);
                            } else if (title.equals(getString(org.tud.bp.fitup.R.string.Challenge))) {
                                notification = new Notification(title, subtext, org.tud.bp.fitup.R.mipmap.ic_fittnessfragebogen, date);
                            }


                            if (notification != null)
                                notifications.add(notification);
                        }

                    }

                    // reminder notification
                    boolean containsReminder = false;
                    for (Notification n : notifications) {
                        if (n.getTitle().equals(getString(org.tud.bp.fitup.R.string.trNotiTitle))) {
                            containsReminder = true;
                            break;
                        }
                    }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity()
                            .getApplicationContext());
                    String nextTrainingTime = preferences.getString("nextTrainingTime", "");
                    if (!containsReminder && !nextTrainingTime.equals("")) {
                        notifications.add(new Notification(
                                getString(org.tud.bp.fitup.R.string.trNotiTitle),
                                getString(org.tud.bp.fitup.R.string.trNotiSmallTitle1)
                                        + " " + Observer.timeTillTraining(nextTrainingTime)
                                        + " " + getString(org.tud.bp.fitup.R.string.trNotiSmallTitle2).split("\n")[0],
                                org.tud.bp.fitup.R.mipmap.ic_fittnessfragebogen, new Date())
                        );
                    }

                    if (notifications != null) {
                        Collections.reverse(notifications);
                        // fill the recycler
                        rv = (RecyclerView) view.findViewById(org.tud.bp.fitup.R.id.recycler);
                        LinearLayoutManager lm = new LinearLayoutManager(getContext());
                        rv.setLayoutManager(lm);
                        // just create a list of tasks
                        rv.setAdapter(new NotificationAdapter(notifications, tbNotificationContent));

                    }

                    // remove all notifications in the notifications bar, cause the user sees them now here
                    NotificationManager notificationManager = (NotificationManager)getActivity().getApplicationContext()
                            .getSystemService(getActivity().getApplicationContext().NOTIFICATION_SERVICE);
                    notificationManager.cancelAll();

                    // close the progress dialog
                    pd.dismiss();

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }

}
