package org.tud.bp.fitup.DataAccessLayer;


import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by M.Braei on 31.01.2017.
 */

public class DAL_Utilities {

    private static String DatabaseURL = "";
    private static String KompassURL = "";

    public static String getDatabaseURL(Context context){
        return context.getString(org.tud.bp.fitup.R.string.SportAppDB_IP);
    }

    public static String getKompassURL(Context context){
        return context.getString(org.tud.bp.fitup.R.string.KompassWeb_IP);
    }

    // helper functions

    //region date conversion functions

    public static String ConvertDateToFirebaseDate(Date date) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMMdd");
        try {
            dateString = sdfr.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static String ConvertDateTimeToString(Date date) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            dateString = sdfr.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static String ConvertDateToString(Date date) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateString = sdfr.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static String ConvertDateTimeToFirebaseString(Date date) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
        try {
            dateString = sdfr.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

    public static Date ConvertFirebaseStringToDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date ConvertFirebaseKeyStringToDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String ConvertFirebaseStringNoSpaceToDateString(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfr = new SimpleDateFormat("dd.MM.yyyy");

        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
            return sdfr.format(convertedDate);
        } catch (ParseException e) {
            //e.printStackTrace();
            return "";
        }
    }

    public static Date ConvertStringToDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String GetTimeAsString() {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("HH:mm:ss");
        try {
            dateString = sdfr.format(new Date());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }
    //endregion


}
