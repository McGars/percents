package com.gars.percents.parcel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by gars on 1/31/14.
 */
public class DataParcel implements Parcelable {

    final static String LOG_TAG = "myLogs";
    /**
     * проценты
     */
    public int   p_procents = 10;
    /**
     * начальный депозит
     */
    public float p_deposit = 0;
    /**
     * ежемесячное пополнение
     */
    public float p_mounth_invite = 0;
    /**
     * на сколько лет депозит
     */
    public int   p_year = 1;
    /**
     * количество вкладчиков
     */
    public float p_portion = 1;
    /**
     * с какого месяца снимать
     */
    public float p_number_month_take_off = 0;
    /**
     * с какого месяца прекратить снимать с депозита
     */
    public float p_number_month_break_take_off = 0;
    /**
     * сколько снимть
     */
    public float p_take_off_how_mach = 0;
    /**
     * в каком месяце прекратить пополнение депозита
     */
    public float p_mounth_break;

    // обычный конструктор
    public DataParcel() {
        Log.d(LOG_TAG, "MyObject(String _s, int _i)");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeInt(p_procents);
        parcel.writeFloat(p_deposit);
        parcel.writeFloat(p_mounth_invite);
        parcel.writeFloat(p_mounth_break);
        parcel.writeInt(p_year);
        parcel.writeFloat(p_portion);
        parcel.writeFloat(p_number_month_take_off);
        parcel.writeFloat(p_number_month_break_take_off);
        parcel.writeFloat(p_take_off_how_mach);
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
        p_mounth_invite = parcel.readFloat();
        p_mounth_break = parcel.readFloat();
        p_year = parcel.readInt();
        p_portion  = parcel.readFloat();
        p_number_month_take_off = parcel.readFloat();
        p_number_month_break_take_off = parcel.readFloat();
        p_take_off_how_mach = parcel.readFloat();
    }

}
