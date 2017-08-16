package com.funini.pyoko;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kei2s
 */



public class AlgorithmFragment extends Fragment implements View.OnClickListener {
    List<ToggleButton> mAlgs = new ArrayList<ToggleButton>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.algorithm, container, false);
    }

    ToggleButton setupToggleButton(int rid){
        ToggleButton ret = (ToggleButton)getActivity().findViewById(rid);
        return ret;
    }

    public void onStart() {
        super.onStart();
        mAlgs.add(setupToggleButton(R.id.alg0));
        mAlgs.add(setupToggleButton(R.id.alg1));
        mAlgs.add(setupToggleButton(R.id.alg2));
        mAlgs.add(setupToggleButton(R.id.alg3));
        for(ToggleButton b : mAlgs){
            b.setOnClickListener(this);
        }
        mAlgs.get(0).callOnClick();
    }

    @Override
    public void onClick(View v) {
        ToggleButton target = (ToggleButton)v;
        for(ToggleButton b : mAlgs){
            if(b == target){
                b.setChecked(true);
            } else {
                b.setChecked(false);
            }
        }
    }
}
