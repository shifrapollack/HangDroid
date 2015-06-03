package com.example.shifra.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class gameOverActivity extends ActionBarActivity {

    int nPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        nPoints = getIntent().getIntExtra("POINTS_IDENTIFIER",0);

        TextView tvPoints  = (TextView)findViewById(R.id.tvPoints);

        tvPoints.setText(String.valueOf(nPoints));

        String correctWord = getIntent().getStringExtra("CORRECT_WORD");
        Toast.makeText(this, "The correct word is " + correctWord, Toast.LENGTH_LONG).show();
    }

    public void saveScore( View view){

        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);

        EditText etName = (EditText) findViewById(R.id.etName);

        String name = etName.getText().toString();

        String previousScores = preferences.getString("SCORES", "");

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("SCORES", name + " " + nPoints + "Point[s] \n" + previousScores);

        editor.commit();

        finish();
    }
}