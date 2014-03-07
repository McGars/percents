package com.gars.procents.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.gars.procents.R;
import com.gars.procents.parcel.DataParcel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
            int count_all_invite = 0;
            float last_incoming = 0;

            for (int i = 0; i < data_parce.p_year*12; i++){
                // procents
                View row = inflater.inflate(R.layout.item_count, null);
                TextView tvYearMonth = (TextView) row.findViewById(R.id.tvYearMonth);
                TextView tvTakeoff = (TextView) row.findViewById(R.id.tvTakeoff);
                TextView tvCount = (TextView) row.findViewById(R.id.tvCount);
                TextView tvIncoming = (TextView) row.findViewById(R.id.tvIncoming);
                TextView tvInvite = (TextView) row.findViewById(R.id.tvInvite);
                TextView tvIncomingProcents = (TextView) row.findViewById(R.id.tvIncomingProcents);
                TextView tvNumberMounth = (TextView) row.findViewById(R.id.tvNumberMounth);

                // порядковый номер
                tvNumberMounth.setText(String.valueOf(i+1));

                // проценты
                float incoming = data_parce.p_deposit * data_parce.p_procents / 100;

                // разница между прошлым и текущим месяце
                if(last_incoming != 0)
                    tvIncomingProcents.setText(formatnumber((int) (incoming - last_incoming)));

                last_incoming = incoming;

                // увеличение депозита
                data_parce.p_deposit = data_parce.p_deposit + incoming;
                // take off limit
                if(data_parce.p_take_off_limit !=0 || data_parce.p_take_off_limit_count != 0
                        //&& data_parce.p_take_off_limit < incoming
                        ){
                    // снятие прибыли в месяц
                    data_parce.p_deposit -= data_parce.p_take_off_limit_count;

                    int _t_k = (int) (data_parce.p_take_off_limit_count / data_parce.p_portion);
                    if(_t_k > 0){
                        tvTakeoff.setTextColor(Color.RED);
                    } else {
                        tvTakeoff.setTextColor(Color.GRAY);
                    }
                    tvTakeoff.setText(formatnumber((int)(data_parce.p_take_off_limit_count/data_parce.p_portion)));
                }else{
                    // пополнение  в месяц из вне
                    data_parce.p_deposit += data_parce.p_mounth_invite;
                    count_all_invite += data_parce.p_mounth_invite;
                }

                if(incoming > 0){
                    tvIncoming.setTextColor(Color.parseColor("#679B00"));
                } else {
                    tvIncoming.setTextColor(Color.RED);
                }
                tvIncoming.setText(formatnumber((int) incoming));
                tvInvite.setText(formatnumber(count_all_invite));

                tvCount.setText(formatnumber((int) data_parce.p_deposit));
                        //String.valueOf(data_parce.p_deposit));
                calendar.add(Calendar.MONTH, 1);
                tvYearMonth.setText(sdf.format(calendar.getTime()));
                tableLayout.addView(row);

                View line = new View(getActivity());
                line.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1));
                line.setBackgroundColor(Color.parseColor("#999999"));
                tableLayout.addView(line);
            }
        }

    }

    private String formatnumber(int i){
        return NumberFormat.getNumberInstance(Locale.US).format(i);
    }
}
