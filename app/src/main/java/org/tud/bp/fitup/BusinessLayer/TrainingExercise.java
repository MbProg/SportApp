package org.tud.bp.fitup.BusinessLayer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Basti on 29.01.2017.
 */

public class TrainingExercise extends Exercise {

    public TrainingExercise() {
        this.weighting = 2.0;
        this.category = "Training";
    }

    public static final Parcelable.Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel parcel) {
            Exercise training = new TrainingExercise() {
            };
            training.name = parcel.readString();
            training.weighting = parcel.readDouble();
            training.timeMinutes = parcel.readInt();
            training.timeHours = parcel.readInt();
            training.category = parcel.readString();
            return training;
        }

        @Override
        public Exercise[] newArray(int i) {
            return new Exercise[i];
        }
    };

}
