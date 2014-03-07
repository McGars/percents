package com.gars.procents.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gars.procents.R;
import com.gars.procents.parcel.DataParcel;

/**
 * Created by gars on 1/31/14.
 */
public class MainFragment extends Fragment {

    private EditText etProcent, etDeposit,etMounthAdd, etYearState, etPortion, etTakeOff, etTakeOffCount;
    private View btStart;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
//        Bundle args = new Bundle();
//        args.putParcelable("data", data);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        etProcent = (EditText) getView().findViewById(R.id.etProcent);
        etDeposit = (EditText) getView().findViewById(R.id.etDeposit);
        etYearState = (EditText) getView().findViewById(R.id.etYearState);
        etMounthAdd = (EditText) getView().findViewById(R.id.etMounthAdd);
        etPortion = (EditText) getView().findViewById(R.id.etPortion);
        etTakeOff = (EditText) getView().findViewById(R.id.etTakeOff);
        etTakeOffCount = (EditText) getView().findViewById(R.id.etTakeOffCount);
        btStart = getView().findViewById(R.id.btStart);
        btStart.setOnClickListener(click_button);
    }


    View.OnClickListener click_button = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btStart){
                DataParcel data = new DataParcel();
                String p_procents = etProcent.getText().toString();
                String p_deposit  = etDeposit.getText().toString();
                String p_mounth   = etMounthAdd.getText().toString();
                String p_year   = etYearState.getText().toString();
                String p_portion  = etPortion.getText().toString();
                String p_take_off = etTakeOff.getText().toString();
                String p_take_off_count = etTakeOffCount.getText().toString();

                if(!p_procents.isEmpty())
                    data.p_procents = Integer.valueOf(p_procents);
                if(!p_deposit.isEmpty())
                    data.p_deposit = Float.valueOf(p_deposit);
                if(!p_mounth.isEmpty())
                    data.p_mounth_invite = Float.valueOf(p_mounth);
                if(!p_year.isEmpty())
                    data.p_year = Integer.valueOf(p_year);
                if(!p_portion.isEmpty())
                    data.p_portion = Float.valueOf(p_portion);
                if(!p_take_off.isEmpty())
                    data.p_take_off_limit = Float.valueOf(p_take_off);
                if(!p_take_off_count.isEmpty())
                    data.p_take_off_limit_count = Float.valueOf(p_take_off_count);


                getActivity().getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, CountFragment.newInstance(data))
                        .commit();
            }
        }
    };


}
