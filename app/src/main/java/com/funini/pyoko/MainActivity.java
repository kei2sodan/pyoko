package com.funini.pyoko;

import android.app.Activity;
import android.os.Bundle;

import com.funini.pyoko.synth.AndroidPlayer;
import com.funini.pyoko.synth.FMAlgorithm;
import com.funini.pyoko.synth.FMOperator;

public class MainActivity extends Activity {
    AndroidPlayer player;
    FMAlgorithm mAlgorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlgorithm = new FMAlgorithm();
        setContentView(R.layout.activity_main);
        //mFMOperator.setHz(440);
        player = new AndroidPlayer(mAlgorithm);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    public synchronized FMOperator getOperator(int i) {
        return mAlgorithm.getOperator(i);
    }

    public synchronized FMAlgorithm getAlgorithm() {
        return mAlgorithm;
    }
}
