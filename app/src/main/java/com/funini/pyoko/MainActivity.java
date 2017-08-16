package com.funini.pyoko;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.funini.pyoko.synth.AndroidPlayer;
import com.funini.pyoko.synth.FMAlgorithm;
import com.funini.pyoko.synth.FMOperator;
import com.funini.pyoko.synth.Operator;

public class MainActivity extends Activity {
    AndroidPlayer player;
    FMAlgorithm mAlgorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlgorithm = new FMAlgorithm();
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for(int i = 0; i < Consts.N_OPERATORS; i++) {
            OperatorFragment fragment = new OperatorFragment();
            fragment.setOperator(mAlgorithm.getOperator(i));
            transaction.add(R.id.operators, fragment);
        }
        transaction.commit();
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
