package com.example.shifra.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class GameMultiActivity extends ActionBarActivity {
    String nWord;
    int nFailCounter = 0;
    int nGuessedLetters = 0;
    int nPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        nWord = getIntent().getStringExtra("PLAYERS_WORD").toUpperCase();
        Log.d("MYLOG", nWord);
        createTextView();

    }

    /**
     * Retrieving the letter introduced in the editText
     *
     * @param view (button clicked)
     */
    public void introduceLetter(View view) {
        EditText myEditText = (EditText)findViewById(R.id.etInsertedLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MYLOG", "The Letter is " + letter);

        if (letter.length() == 1){
            checkLetter(letter.toUpperCase());
        }else{
            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * checking if letter entered matches any letter in the word to guess
     *
     * @param introduceLetter, letter entered by the user
     */
    public void checkLetter(String introduceLetter) {

        char charIntroducedLetter = introduceLetter.charAt(0);
        boolean letterGuessed = false;

        for (int i = 0; i < nWord.length(); i++) {

            char charFromWord = nWord.charAt(i);

            if (charFromWord == charIntroducedLetter) {

                Log.d("MYLOG", "There was one match");

                letterGuessed = true;

                showLettersAtIndex(i, charIntroducedLetter);

                nGuessedLetters++;
            }
        }

        if (!letterGuessed){
            displayFailedLetter(introduceLetter);
            letterFailed();
        }if(nGuessedLetters == nWord.length()){
          finish();

        }

    }

    public void createTextView(){

        LinearLayout layoutLetters = (LinearLayout)findViewById(R.id.layoutLetters);

        for(int i = 0; i <nWord.length(); i++){
            TextView myTextView = (TextView)getLayoutInflater().inflate(R.layout.textview, null);

           layoutLetters.addView(myTextView);
        }


    }



    /**
     * Displaying a letter passed by the user
     *
      * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));


    }

    public void letterFailed(){

        nFailCounter++;

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if(nFailCounter == 1){
            imageView.setImageResource(R.drawable.hangdroid_1);
        }else if(nFailCounter == 2){
            imageView.setImageResource(R.drawable.hangdroid_2);
        } else if(nFailCounter == 3) {
            imageView.setImageResource(R.drawable.hangdroid_3);
        }else if(nFailCounter == 4){
            imageView.setImageResource(R.drawable.hangdroid_4);
        }else if(nFailCounter == 5) {
            imageView.setImageResource(R.drawable.hangdroid_5);
        }else if(nFailCounter == 6){
            Intent gameOverIntent = new Intent(this, gameOverActivity.class);

            gameOverIntent.putExtra("POINTS_IDENTIFIER", nPoints);
            gameOverIntent.putExtra("CORRECT_WORD", nWord);

            startActivity(gameOverIntent);

            finish();
        }
    }

    /**
     * displays the failed letter in red in the textview
     *
     * @param introduceLetter
     */
    public  void displayFailedLetter(String introduceLetter) {
        boolean isGuessed = false;

        TextView tvIncorrectLetters = (TextView) findViewById(R.id.tvIncorrectLetters);

        String incorrectLetters = tvIncorrectLetters.getText().toString();

        for(int i = 0; i < incorrectLetters.length(); i++){

            if(incorrectLetters.charAt(i) == introduceLetter.charAt(0)){
                --nFailCounter;
                isGuessed = true;
                Toast.makeText(this, "You already entered this letter", Toast.LENGTH_SHORT).show();
            }
        }
        if(!isGuessed)
            tvIncorrectLetters.setText(incorrectLetters + " " + introduceLetter);
    }

}
