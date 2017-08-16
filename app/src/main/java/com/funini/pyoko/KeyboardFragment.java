package com.funini.pyoko;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kei2s
 */



public class KeyboardFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    SeekBar mSeekBarFreq;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.keyboard, container, false);
    }

    SeekBar setupSeekBar(int rid){
        SeekBar ret = (SeekBar)getActivity().findViewById(rid);
        ret.setOnSeekBarChangeListener(this);
        return ret;
    }

    public void onStart() {
        super.onStart();
        mSeekBarFreq = setupSeekBar(R.id.sb_freq);
    }
    public void onProgressChanged(SeekBar seekBar,
                                  int progress, boolean fromUser) {
        double freq = percentToFreq(progress);
        if(freq > Consts.SAMPLING_RATE){
            freq = Consts.SAMPLING_RATE;
        }
        //mTvFreq.setText("Freq: " + freq + " Hz");
        //mSynth.setSndFreq(freq);
    }

    private double percentToFreq(int progress) {
        double ret = 110 * Math.pow(1.03, progress*0.1);
        return ret;
    }



    public void onStartTrackingTouch(SeekBar seekBar) {
        //mSynth.setVolume(Synth.VOL_MAX);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        //mSynth.setVolume(0);
    }
}
