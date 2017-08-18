package com.funini.pyoko;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.funini.pyoko.synth.AndroidPlayer;
import com.funini.pyoko.synth.FMAlgorithm;
import com.funini.pyoko.synth.FMOperator;

public class MainActivity extends Activity {
    AndroidPlayer mAndroidPlayer;
    FMAlgorithm mAlgorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlgorithm = new FMAlgorithm();
        mAndroidPlayer = new AndroidPlayer(mAlgorithm);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for(int i = 0; i < Consts.N_OPERATORS; i++) {
            OperatorFragment fragment = new OperatorFragment();
            fragment.setOperator(mAlgorithm.getOperator(i));
            if(i < 2) {
                transaction.add(R.id.operators0, fragment);
            } else {
                transaction.add(R.id.operators1, fragment);
            }
        }
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAndroidPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAndroidPlayer.stop();
    }

    public synchronized FMOperator getOperator(int i) {
        return mAlgorithm.getOperator(i);
    }

    public synchronized FMAlgorithm getAlgorithm() {
        return mAlgorithm;
    }

    public AndroidPlayer getAndroidPlayer() {
        return mAndroidPlayer;
    }
}
