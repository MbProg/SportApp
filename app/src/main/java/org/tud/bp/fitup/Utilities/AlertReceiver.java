package org.tud.bp.fitup.Utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.tud.bp.fitup.Observe.ObserverChallengeInvitation;
import org.tud.bp.fitup.Observe.ObserverFitnessFragebogen;
import org.tud.bp.fitup.Observe.ObserverStudioSetRemoveNotification;
import org.tud.bp.fitup.DataAccessLayer.DAL_Utilities;
import org.tud.bp.fitup.Observe.ObserverBsaFragebogen;
import org.tud.bp.fitup.Observe.ObserverChallengeWinner;
import org.tud.bp.fitup.Observe.ObserverMotivationMessage;
import org.tud.bp.fitup.Observe.ObserverStimmungAngabe;
import org.tud.bp.fitup.Observe.ObserverStudioNotification;
import org.tud.bp.fitup.Observe.ObserverTrainQuestioning;
import org.tud.bp.fitup.Observe.ObserverTrainingReminder;
import com.firebase.client.Firebase;

import java.util.Date;

/**
 * Created by M.Braei on 23.03.2017.
 */

public class AlertReceiver extends BroadcastReceiver {
    // Although we use some kind of observer pattern here,  it a little bit different, the observable has no
    // add/remove observer functions. Because this class is called by another process, so it is not possible to add
    // observers at runtime.
    // so the observers has to be declare right here

    // Here delcare your observer
    ObserverStimmungAngabe observerStimmungAngabe = null;
    ObserverTrainingReminder observerTrainingReminder = null;
    ObserverMotivationMessage observerMotivationMessage = null;
    ObserverTrainQuestioning observerTrainQuestioning = null;
    ObserverFitnessFragebogen observerFitnessFragebogen = null;
    ObserverBsaFragebogen observerBsaFragebogen = null;
    ObserverChallengeInvitation observerChallengeInvite = null;
    ObserverStudioSetRemoveNotification observerStudioSetRemoveNotification = null;
    ObserverStudioNotification observerStudioNotification = null;
    ObserverChallengeWinner observerChallengeWinner = null;

    // Called when a broadcast is made targeting this class
    @Override
    public void onReceive(Context context, Intent intent) {

        // uncomment this line of code to debug notifications
        // then use Run-> Attach to Debugger to Andrdoid process to fire breakpoints
        //android.os.Debug.waitForDebugger();

        // Initialize your observer and call its update method
        if (observerStimmungAngabe == null)
            observerStimmungAngabe = new ObserverStimmungAngabe();
        observerStimmungAngabe.update(context);

        if (observerTrainingReminder == null)
            observerTrainingReminder = new ObserverTrainingReminder(context);
        observerTrainingReminder.update(context);

        if (observerMotivationMessage == null)
            observerMotivationMessage = new ObserverMotivationMessage();
        observerMotivationMessage.update(context);

        if (observerTrainQuestioning == null)
            observerTrainQuestioning = new ObserverTrainQuestioning();
        observerTrainQuestioning.update(context);

        if (observerFitnessFragebogen == null)
            observerFitnessFragebogen = new ObserverFitnessFragebogen();
        observerFitnessFragebogen.update(context);

        if (observerBsaFragebogen == null)
            observerBsaFragebogen = new ObserverBsaFragebogen();
        observerBsaFragebogen.update(context);

        if (observerChallengeInvite == null)
            observerChallengeInvite = new ObserverChallengeInvitation();
        observerChallengeInvite.update(context);

        if (observerStudioSetRemoveNotification == null)
            observerStudioSetRemoveNotification = new ObserverStudioSetRemoveNotification(context);
        observerStudioSetRemoveNotification.update(context);

        if (observerStudioNotification == null)
            observerStudioNotification = new ObserverStudioNotification();
        observerStudioNotification.update(context);

        if (observerChallengeWinner == null)
            observerChallengeWinner = new ObserverChallengeWinner();
        observerChallengeWinner.update(context);

        insertdb(context);
    }

    void insertdb(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + preferences.getString
                ("logedIn", "") + "/");
        ref.child("AlertReceiver").setValue(new Date().toString());

    }
}
