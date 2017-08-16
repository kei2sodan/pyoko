package com.funini.pyoko;

import android.app.Activity;
import android.os.Bundle;

import com.funini.pyoko.synth.AndroidPlayer;
import com.funini.pyoko.synth.FMOperator;
import com.funini.pyoko.synth.Operator;
import com.funini.pyoko.synth.SinOperator;

public class MainActivity extends Activity {
    AndroidPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Operator op = new SinOperator();
        op.setHz(440);
        player = new AndroidPlayer(op);
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
}
