package org.tud.bp.fitup.Objects;

import org.tud.bp.fitup.BusinessLayer.DiaryEntry;

import java.util.ArrayList;

/**
 * Created by Basti on 16.01.2017.
 */

public class AllDiaryEntries {

    //Create an object of SingleObject
    private static AllDiaryEntries allDiaryEntries;
    private ArrayList<DiaryEntry> diaryList;

    //set constructor private cause of singleton
    private AllDiaryEntries() {
        diaryList = new ArrayList<DiaryEntry>();
    }

    ;

    /**
     * This method returns the singleton object of AllDiaryEntires
     *
     * @return the only one object
     */
    public static AllDiaryEntries getInstance() {
        if (allDiaryEntries == null)
            allDiaryEntries = new AllDiaryEntries();

        return allDiaryEntries;
    }

    public void add(DiaryEntry diaryEntry) {
        diaryList.add(diaryEntry);
    }

    /**
     * This method returns the list with all diary entries.
     *
     * @return ArrayList with all diary entries
     */
    public ArrayList<DiaryEntry> getDiaryList() {
        return diaryList;
    }


}
