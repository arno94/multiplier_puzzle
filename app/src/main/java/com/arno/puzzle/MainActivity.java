package com.arno.puzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ImageView[] pieces;
    private ToggleButton[] buttons;
    private Button btRestart;
    private LinearLayout[] layoutsTop;
    private LinearLayout[] layoutsBot;

    private int lastChecked = 0;

    private int[] sums = new int[]{ 1200, 2400, 500, 3600, 200, 1800};

    private int[] answers = new int[] { 200, 1800, 3600, 2400, 1200, 500 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRestart = (Button)findViewById(R.id.bt_restart);
        btRestart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        layoutsTop = new LinearLayout[6];
        layoutsBot = new LinearLayout[6];

        for(int i = 0;i < layoutsTop.length; i++){
            int resID = getResources().getIdentifier("ll_" + (i+1) + "_1", "id", getPackageName());
            layoutsTop[i] = (LinearLayout) findViewById(resID);

            int resIDBot = getResources().getIdentifier("ll_" + (i+1) + "_2", "id", getPackageName());
            layoutsBot[i] = (LinearLayout) findViewById(resIDBot);
        }

        pieces = new ImageView[6];
        for(int i = 0;i < pieces.length; i++) {
            int resID = getResources().getIdentifier("piece_" + (i+1), "id", getPackageName());
            pieces[i] = (ImageView) findViewById(resID);
            final int j = i;
            pieces[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sums[lastChecked] == answers[j]){
                        int id = getResources().getIdentifier("piece_" + (j+1), "drawable", getPackageName());
                        pieces[j].setImageResource(id);
                        layoutsTop[j].setVisibility(View.INVISIBLE);
                        layoutsBot[j].setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        buttons = new ToggleButton[6];
        for(int i = 0;i < buttons.length; i++) {
            int resID = getResources().getIdentifier("bt_" + (i+1), "id", getPackageName());
            buttons[i] = (ToggleButton) findViewById(resID);
            buttons[i].setTextOn(sums[i] + "");
            buttons[i].setTextOff(sums[i] + "");
            buttons[i].setText(sums[i] + "");
            final int j = i;
            buttons[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int k = 0;k < buttons.length; k++){
                        if(k != j) buttons[k].setChecked(false);
                    }
                    buttons[j].setChecked(true);
                    lastChecked = j;
                }
            });
        }
    }

    private void restart(){
        for(int i = 0;i < pieces.length; i++){
            pieces[i].setImageResource(R.drawable.placeholder);
        }
        lastChecked = 0;
        buttons[0].setChecked(true);
        for(int i = 1;i < buttons.length; i++) buttons[i].setChecked(false);
        for(int i = 0;i < layoutsTop.length; i++) {
            layoutsTop[i].setVisibility(View.VISIBLE);
            layoutsBot[i].setVisibility(View.VISIBLE);
        }
    }
}
