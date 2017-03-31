package org.tud.bp.fitup.BusinessLayer;

import android.content.Context;

import org.tud.bp.fitup.DataAccessLayer.DAL_RegisteredUsers;
import com.firebase.client.DataSnapshot;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * catch registration data
 * Created by Aziani on 25.03.2017.
 */

public class RegisterCatcher {

    Thread t;
    LinkedList<String> oldRegisteredUsers = new LinkedList<>();
    User user;

    public void catchRegistration(User user, final Context context) {
        this.user = user;
        DAL_RegisteredUsers.loadRegistration(RegisterCatcher.this, context);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    DAL_RegisteredUsers.loadRegistration(RegisterCatcher.this, context);
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();

    }

    /**
     * return texts of registered users
     *
     * @param dataSnapshot snapshot with users
     */
    public void returnRegistrations(DataSnapshot dataSnapshot, Context context) {
        if (dataSnapshot == null) {
            return;
        }
        // check if old registrations saved
        if (oldRegisteredUsers.size() == 0) {
            oldRegisteredUsers = fillList(dataSnapshot);
        } else {
            LinkedList<String> difference = fillList(dataSnapshot);
            LinkedList<String> removeList = new LinkedList<>();
            // remove copies
            for (Iterator<String> iterator = difference.iterator(); iterator.hasNext(); ) {
                String d = iterator.next();
                for (String o : oldRegisteredUsers) {
                    if (d.equals(o)) {
                        removeList.add(d);
                    }
                }
            }
            difference.removeAll(removeList);
            if (difference.size() > 0) {
                DAL_RegisteredUsers.insertMail(difference.getFirst(), user, context);
                user.setEmail(difference.getFirst());
                if (t != null) {
                    t.interrupt();
                    t = null;
                }

            }
        }
    }

    public LinkedList<String> fillList(DataSnapshot dataSnapshot) {
        LinkedList<String> list = new LinkedList<>();

        // fill list with registrations
        for (DataSnapshot d : dataSnapshot.getChildren()) {
            list.add((String) d.child("email").getValue());
        }
        return list;
    }

}
