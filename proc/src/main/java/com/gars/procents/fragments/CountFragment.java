package com.gars.procents.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.gars.procents.R;
import com.gars.procents.parcel.DataParcel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by gars on 1/31/14.
 */
public class CountFragment extends Fragment {

    private DataParcel data_parce;
    private TableLayout tableLayout;
    private LayoutInflater inflater;
    private SimpleDateFormat sdf;

    public static CountFragment newInstance(Parcelable data) {
        CountFragment fragment = new CountFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View v = inflater.inflate(R.layout.fragment_count_page, null);
        tableLayout = (TableLayout)v.findViewById(R.id.tableLayout);

        data_parce = getArguments().getParcelable("data");
        sdf = new SimpleDateFormat("yyyy.MM");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(data_parce!=null){
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < data_parce.p_year*12; i++){
                // procents
                View row = inflater.inflate(R.layout.item_count, null);
                TextView tvYearMonth = (TextView) row.findViewById(R.id.tvYearMonth);
                TextView tvTakeoff = (TextView) row.findViewById(R.id.tvTakeoff);
                TextView tvCount = (TextView) row.findViewById(R.id.tvCount);
                TextView tvIncoming = (TextView) row.findViewById(R.id.tvIncoming);

                float incoming = data_parce.p_deposit * data_parce.p_procents / 100;

                data_parce.p_deposit = data_parce.p_deposit + data_parce.p_mounth_add_cache
                        + incoming;
                // take off limit
                if(data_parce.p_take_off_limit !=0 && data_parce.p_take_off_limit_count != 0 &&
                        data_parce.p_take_off_limit > data_parce.p_deposit
                        ){
                    data_parce.p_deposit -= data_parce.p_take_off_limit_count;
                    tvTakeoff.setText(String.valueOf(data_parce.p_take_off_limit_count/data_parce.p_portion));
                }
                tvIncoming.setText(String.format("%.02f", incoming));

                tvCount.setText(String.format("%.02f", data_parce.p_deposit));
                        //String.valueOf(data_parce.p_deposit));
                calendar.add(Calendar.MONTH, 1);
                tvYearMonth.setText(sdf.format(calendar.getTime()));
                tableLayout.addView(row);
            }
        }

    }
}
