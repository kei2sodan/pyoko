package com.funini.pyoko;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.funini.pyoko.synth.FMAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kei2s
 */

public class AlgorithmFragment extends Fragment implements View.OnClickListener {
    List<ToggleButton> mAlgs = new ArrayList<>();
    FMAlgorithm mAlgorithm;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.algorithm, container, false);
    }

    ToggleButton setupToggleButton(int rid){
        ToggleButton ret = (ToggleButton)(getView().findViewById(rid));
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
        for(int i = 0; i < mAlgs.size(); i++){
            ToggleButton b = mAlgs.get(i);
            if(b == target){
                b.setChecked(true);
                mAlgorithm.setAlgorithm(i);
            } else {
                b.setChecked(false);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof MainActivity)) {
            throw new UnsupportedOperationException(
                    "Unknown activity");
        } else {
            mAlgorithm = ((MainActivity)context).getAlgorithm();
        }
    }
}
