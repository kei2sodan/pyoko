package com.funini.pyoko;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.funini.pyoko.synth.Operator;

/**
 * Created by kei2s
 */
public class KeyboardFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    SeekBar mSeekBarFreq;
    TextView mTvFreq;
    Operator mOperator;
    public final static double KEY_BASE = Math.log(2)/12;
    public final static int[] WHITE_KEYS = {0,4,7,};;//{0,2,4,5,7,9,11,};
    //public final static int[] CODE_KEYS = {0,4,7,};
    public final static float[] mFreqs = new float[25];
    static {
        for(int i = 0; i < mFreqs.length; i++){
            int octave = i / WHITE_KEYS.length;
            int idx = i % WHITE_KEYS.length;
            double pow = WHITE_KEYS[idx] + octave * 12;
            mFreqs[i] = 261.626f  * (float)Math.exp(KEY_BASE * pow);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.keyboard, container, false);
    }

    SeekBar setupSeekBar(int rid){
        SeekBar ret = (SeekBar)getView().findViewById(rid);
        ret.setOnSeekBarChangeListener(this);
        return ret;
    }


    public void onStart() {
        super.onStart();
        mSeekBarFreq = setupSeekBar(R.id.sb_keys);
        mTvFreq = (TextView) getView().findViewById(R.id.tv_key_freq);
    }

    public void onProgressChanged(SeekBar seekBar,
                                  int progress, boolean fromUser) {
        float hz = mFreqs[progress];
        mTvFreq.setText("Note:" + progress + ", Freq: " + hz + " Hz");
        mOperator.setHz(hz);
        mOperator.noteOn();
    }

//    private float percentToFreq(float ratio) {
//        float ret = 440 * (float)Math.pow(8.0, (ratio - 0.5));
//        return ret;
//    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        mOperator.noteOn();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        mOperator.noteOff();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof MainActivity)) {
            throw new UnsupportedOperationException(
                    "Unknown activity");
        } else {
            mOperator = ((MainActivity)context).getAlgorithm();
        }
    }
}
