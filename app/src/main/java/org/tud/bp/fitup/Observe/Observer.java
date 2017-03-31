package org.tud.bp.fitup.Observe;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import org.tud.bp.fitup.DataAccessLayer.DAL_Utilities;
import org.tud.bp.fitup.R;
import com.firebase.client.Firebase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by M.Braei on 24.03.2017.
 */

public abstract class Observer {

    abstract public void update(Context context);

    protected Context context;
    protected SharedPreferences preferences;

    /**
     * The public function to outside which saves the notification in the db and shows out on the status bar of the
     * device
     *
     * @param context      the pending activity
     * @param strategyname the name of the strategy: will be saved in the database
     * @param cls          the classname of the activity that is going to be called when the user clicks on the
     *                     notification
     * @param title        the title of the notification
     * @param text         the text of the notification
     * @param icon         the icon of the notification that is shown
     */
    public void sendNotification(Context context, String strategyname, Class<?> cls, String title, String text,
                                 Integer icon) {
        // first save the data in database
        String sDate = saveNotificationDB(context, strategyname, text);
        // now show the notification
        if (!sDate.equals(""))
            createNotification(context, sDate, cls, title, text, R.drawable.ic_notification);

    }

    /**
     * The public function to outside which saves the notification in the db and shows out on the status bar of the
     * device
     *
     * @param context      the pending activity
     * @param strategyname the name of the strategy: will be saved in the database
     * @param cls          the classname of the activity that is going to be called when the user clicks on the
     *                     notification
     * @param title        the title of the notification
     * @param text         the text of the notification
     * @param icon         the icon of the notification that is shown
     * @param object       the object that has to be sent to the class cls
     * @param objectname   the name of the object that has to be sent to the bundle
     */
    public void sendNotification(Context context, String strategyname, Class<?> cls, String title, String text,
                                 Integer icon, Serializable object, String objectname) {
        // first save the data in database
        String sDate = saveNotificationDB(context, strategyname, text);
        // now show the notification
        if (!sDate.equals(""))
            createNotification(context, sDate, cls, title, text, R.drawable.ic_notification, object, objectname);

    }

    /**
     * Here the notification will be saved in Firebase to prevent multiple notifications for the same issue
     *
     * @param context      The context
     * @param strategyName Give the strategyname and the notification will be saved under the current user with this
     *                     strategy name
     * @param text         The text of the notification
     * @return
     */
    public String saveNotificationDB(Context context, String strategyName, String text) {
        try {
            // get the current user
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context
                    .getApplicationContext());

            // build the current URL
            Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + preferences.getString
                    ("logedIn", "") + "/Notifications/");

            // first the main node class of the class and the date as sub node
            String sDate = DAL_Utilities.ConvertDateTimeToFirebaseString(new Date());
            Firebase classRef = ref.child(strategyName).child(sDate);
            // now enter the the text of the notification as key-value
            Firebase node = classRef.child("subText");
            node.setValue(text);

            return sDate;

        } catch (Exception ex) {
            String s = ex.getMessage();
            return "";
        } finally {

        }
    }

    /**
     * create and display the notification to the user and when the user clicks on it, a specific activity is shown
     *
     * @param context          context of the app
     * @param NotificationDate the date of the notification. Is used to be sent to the activity to delete it from
     *                         firebase when the activity is shown and the notification is clicked. So the
     *                         notificaiton disappears from the tbNotification
     * @param cls              the class name of the activity that is going to be shown when clicked on the notification
     * @param title            title of the notification
     * @param text             text of the notification
     * @param icon             icon that is shown on the notification
     */
    public void createNotification(Context context, String NotificationDate, Class<?> cls, String title, String text,
                                   Integer icon) {

        Intent contentClass = new Intent(context, cls);
        contentClass.putExtra("NotificationDate", NotificationDate);                                             //
        // we have to know the notification date to delete from db
        contentClass.putExtra("Vor", text.equals(context.getString(R.string.ntf_stimmungsabgabe)) ? "1" : "0");
        // that we should know to save it in the V node or N node (is it the question before or after the training)

        // Used to stack tasks across activites so we go to the proper place when back is clicked
        // create(context): context is the context that will launch the new task stack or a PendingIndent
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);


        // Add all parents of this activity to the stack
        // The parentstck of MoreInfoNotifaction is defined in the Manifest ->
        // <android:parentActivityName=".MainActivity">
        tStackBuilder.addParentStack(cls);

        // Add our new Intent to the stack
        tStackBuilder.addNextIntent(contentClass);

        PendingIntent notificIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

// Define an Intent and an action to perform with it by another application
 /*       PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MoreInfoNotification.class), 0);
*/
        // Builds a notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setContentTitle(title)                         // The title that is displayed
                        .setContentText(text)              // This text is shown by the notification
                        .setTicker(text);            // This is not shown since Android 5.0 but still usefull to
        // accessibility services

        // Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(notificIntent);

        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        mBuilder.setDefaults(Notification.DEFAULT_ALL);

        // Auto cancels the notification when clicked on in the task bar
        // gets the notif away from the task bar
        mBuilder.setAutoCancel(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // get a random ID
        Random r = new Random();
        Integer id = r.nextInt();

        // Post the notification
        mNotificationManager.notify(id, mBuilder.build());

    }

    /**
     * create and display the notification to the user and when the user clicks on it, a specific activity is shown
     *
     * @param context          context of the app
     * @param NotificationDate the date of the notification. Is used to be sent to the activity to delete it from
     *                         firebase when the activity is shown and the notification is clicked. So the
     *                         notificaiton disappears from the tbNotification
     * @param cls              the class name of the activity that is going to be shown when clicked on the notification
     * @param title            title of the notification
     * @param text             text of the notification
     * @param icon             icon that is shown on the notification
     * @param object           object that has to be sent to cls
     * @param objectname       the name of the object set in the bundle to cls
     */
    public void createNotification(Context context, String NotificationDate, Class<?> cls, String title, String text,
                                   Integer icon, Serializable object, String objectname) {

        Intent contentClass = new Intent(context, cls);
        contentClass.putExtra("NotificationDate", NotificationDate);                                             //
        // we have to know the notification date to delete from db
        contentClass.putExtra(objectname, object);     // that we should know to save it in the V node or N node (is
        // it the question before or after the training)

        // Used to stack tasks across activities so we go to the proper place when back is clicked
        // create(context): context is the context that will launch the new task stack or a PendingIndent
        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(context);


        // Add all parents of this activity to the stack
        // The parentstck of MoreInfoNotifaction is defined in the Manifest ->
        // <android:parentActivityName=".MainActivity">
        tStackBuilder.addParentStack(cls);

        // Add our new Intent to the stack
        tStackBuilder.addNextIntent(contentClass);

        PendingIntent notificIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

// Define an Intent and an action to perform with it by another application
 /*       PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MoreInfoNotification.class), 0);
*/
        // Builds a notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setContentTitle(title)                         // The title that is displayed
                        .setContentText(text)              // This text is shown by the notification
                        .setTicker(text);            // This is not shown since Android 5.0 but still usefull to
        // accessibility services

        // Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(notificIntent);

        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        mBuilder.setDefaults(Notification.DEFAULT_ALL);

        // Auto cancels the notification when clicked on in the task bar
        // gets the notif away from the task bar
        mBuilder.setAutoCancel(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // get a random ID
        Random r = new Random();
        Integer id = r.nextInt();

        // Post the notification
        mNotificationManager.notify(id, mBuilder.build());

    }

    public String getCurrentWeekday() {
        String dayOfWeek;

        // setup calendar to get day of week
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // determine the day of the week
        int currentWeekday = calendar.get(Calendar.DAY_OF_WEEK);
        if (currentWeekday == Calendar.MONDAY) {
            dayOfWeek = "Montag";
        } else if (currentWeekday == Calendar.TUESDAY) {
            dayOfWeek = "Dienstag";
        } else if (currentWeekday == Calendar.WEDNESDAY) {
            dayOfWeek = "Mittwoch";
        } else if (currentWeekday == Calendar.THURSDAY) {
            dayOfWeek = "Donnerstag";
        } else if (currentWeekday == Calendar.FRIDAY) {
            dayOfWeek = "Freitag";
        } else if (currentWeekday == Calendar.SATURDAY) {
            dayOfWeek = "Samstag";
        } else {
            dayOfWeek = "Sonntag";
        }
        return dayOfWeek;
    }

    /**
     * determine the time in minutes needed until the training begins
     *
     * @param trainingStartTime string, representing the training start time
     * @return time in minutes until training starts
     */
    public static int timeTillTraining(String trainingStartTime) {
        // start of the training
        int trainingHour = Integer.valueOf(trainingStartTime.split(":")[0]);
        int trainingMinute = Integer.valueOf(trainingStartTime.split(":")[1]);
        int trainingMinuteOfDay = trainingHour * 60 + trainingMinute;

        // current time
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentMinuteOfDay = currentHour * 60 + currentMinute;

        return trainingMinuteOfDay - currentMinuteOfDay;
    }

    public String getNextTrainingTimeString(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String preferenceString = preferences.getString(getCurrentWeekday(), "");
        System.out.println(preferenceString);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        // find next training start time
        if (!preferenceString.equals("")) {
            for (final String s : preferenceString.split(";")) {
                int trainingMinuteOfDay = Integer.valueOf(s.split(":")[0]) * 60
                        + Integer.valueOf(s.split(":")[1]);
                int currentMinuteOfDay = currentHourOfDay * 60 + currentMinute;
                // check if training is still noteworthy
                if (currentMinuteOfDay <= trainingMinuteOfDay) {
                    return s;
                }
            }
        }
        return "";
    }

    public String getLastTrainingTimeString(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String preferenceString = preferences.getString(getCurrentWeekday(), "");
        String lastTraining = "";
        Calendar calendar = Calendar.getInstance();
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        // find next training start time
        if (!preferenceString.equals("")) {
            for (final String s : preferenceString.split(";")) {
                int trainingMinuteOfDay = Integer.valueOf(s.split(":")[0]) * 60
                        + Integer.valueOf(s.split(":")[1]);
                int currentMinuteOfDay = currentHourOfDay * 60 + currentMinute;
                // check if training is still noteworthy
                if (currentMinuteOfDay <= trainingMinuteOfDay) {
                    if (!lastTraining.equals("")) {
                        return lastTraining;
                    } else {
                        break;
                    }
                } else {
                    lastTraining = s;
                }
            }
        }
        return lastTraining;
    }

    /**
     * get the time of the next training time
     *
     * @param context
     * @return
     */
    final public Integer getNextTrainingTimeInteger(Context context) {
        String preferenceString = preferences.getString(getCurrentWeekday(), "");
        insertTrainingdates(preferenceString);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        // find next training start time
        if (!preferenceString.equals("")) {
            for (final String s : preferenceString.split(";")) {
                int trainingMinuteOfDay = Integer.valueOf(s.split(":")[0]) * 60
                        + Integer.valueOf(s.split(":")[1]);
                int currentMinuteOfDay = currentHourOfDay * 60 + currentMinute;
                // check if training is still noteworthy
                if (currentMinuteOfDay <= trainingMinuteOfDay) {
                    return trainingMinuteOfDay;
                }
            }
        }
        return 0;
    }

    private void insertTrainingdates(String dates) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Firebase ref = new Firebase(DAL_Utilities.getDatabaseURL(context) + "users/" + preferences.getString
                ("logedIn", "") + "/");
        ref.child("TrainingDates").setValue(dates);

    }
    /**
     * get the time of the last training time
     * @param context
     * @return
     */
    /**
     * get the time of the last training time
     *
     * @param context
     * @return
     */
    public Integer getLastTrainingTime(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String preferenceString = preferences.getString(getCurrentWeekday(), "");
        String lastTraining = "";
        Calendar calendar = Calendar.getInstance();
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        // find next training start time
        if (!preferenceString.equals("")) {
            for (final String s : preferenceString.split(";")) {
                int trainingMinuteOfDay = Integer.valueOf(s.split(":")[0]) * 60
                        + Integer.valueOf(s.split(":")[1]);
                int currentMinuteOfDay = currentHourOfDay * 60 + currentMinute;
                // check if training is still noteworthy
                if (currentMinuteOfDay <= trainingMinuteOfDay) {
                    if (!lastTraining.equals("")) {
                        return Integer.valueOf(lastTraining.split(":")[0]) * 60
                                + Integer.valueOf(lastTraining.split(":")[1]);
                    } else {
                        break;
                    }
                } else {
                    lastTraining = s;
                }
            }
        }
        if (lastTraining.equals(""))
            return 0;
        else {
            return Integer.valueOf(lastTraining.split(":")[0]) * 60
                    + Integer.valueOf(lastTraining.split(":")[1]);
        }

    }
}
