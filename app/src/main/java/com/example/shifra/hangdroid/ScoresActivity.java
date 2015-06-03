package com.example.shifra.hangdroid;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScoresActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", MODE_PRIVATE);
        String scores = preferences.getString("SCORES", "NO SCORES");

        TextView tvScores = (TextView)findViewById(R.id.tvScores);

        tvScores.setText(scores);
    }


}
