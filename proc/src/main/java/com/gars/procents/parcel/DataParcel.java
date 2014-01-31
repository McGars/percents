package com.gars.procents.parcel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by gars on 1/31/14.
 */
public class DataParcel implements Parcelable {

    final static String LOG_TAG = "myLogs";
    // etProcent, etDeposit,etMounthAdd, etYearState, etPortion, etTakeOff, etTakeOffCount;
    public int   p_procents = 10;
    public float p_deposit = 0;
    public float p_mounth_add_cache = 0;
    public int   p_year = 1;
    public float p_portion = 1;
    public float p_take_off_limit = 0;
    public float p_take_off_limit_count = 0;

    // обычный конструктор
    public DataParcel() {
        Log.d(LOG_TAG, "MyObject(String _s, int _i)");
    }

    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeInt(p_procents);
        parcel.writeFloat(p_deposit);
        parcel.writeFloat(p_mounth_add_cache);
        parcel.writeInt(p_year);
        parcel.writeFloat(p_portion);
        parcel.writeFloat(p_take_off_limit);
        parcel.writeFloat(p_take_off_limit_count);
    }

    public static final Parcelable.Creator<DataParcel> CREATOR = new Parcelable.Creator<DataParcel>() {
        // распаковываем объект из Parcel
        public DataParcel createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new DataParcel(in);
        }

        public DataParcel[] newArray(int size) {
            return new DataParcel[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private DataParcel(Parcel parcel) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        p_procents = parcel.readInt();
        p_deposit  = parcel.readFloat();
        p_mounth_add_cache  = parcel.readFloat();
        p_year = parcel.readInt();
        p_portion  = parcel.readFloat();
        p_take_off_limit  = parcel.readFloat();
        p_take_off_limit_count  = parcel.readFloat();
    }

}
