package com.funini.pyoko;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.funini.pyoko.synth.Operator;

/**
 * Created by kei2s
 */
public class SequencerFragment extends Fragment implements Button.OnClickListener {
    Button mButton;
    Operator mOperator;

    @Override
    public void onClick(View view) {
        new Player().start();
    }

    //Handler mHandler = new Handler();
    class Player extends Thread {
        float[] SCORE = {523.251f, 659.255f, 587.330f, 391.995f};

        private void playOneNote(float hz){
            mOperator.setHz(hz);
            mOperator.noteOn();
            sleep(400);
            mOperator.noteOff();
            sleep(100);
        }

        void sleep(int ms){
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            for(float hz : SCORE){
                playOneNote(hz);
            }
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sequencer, container, false);
    }

    Button setupButton(int rid){
        Button ret = (Button)getView().findViewById(rid);
        ret.setOnClickListener(this);
        return ret;
    }

    public void onStart() {
        super.onStart();
        mButton = setupButton(R.id.button_chime);
    }

    private float percentToFreq(float ratio) {
        float ret = 440 * (float)Math.pow(8.0, (ratio - 0.5));
        return ret;
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
