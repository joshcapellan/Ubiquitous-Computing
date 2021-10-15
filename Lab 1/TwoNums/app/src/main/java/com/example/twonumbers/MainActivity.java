package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    Button button1, button2;
    int rn1, rn2, scoreNo;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        score = findViewById(R.id.textView);
        scoreNo = 0;
        RandomNo();
    }

    public void RandomNo(){
        Random rand = new Random();
        rn1 = rand.nextInt(10);
        rn2 = rand.nextInt(10);

        ///n1 = findViewById(R.id.n1);
        button1 = findViewById(R.id.button);
        button1.setText(Integer.toString(rn1));

        //n2 = findViewById(R.id.n2);
        button2 = findViewById(R.id.button2);
        button2.setText(Integer.toString(rn2));

        score = findViewById(R.id.textView);
        score.setText("Your score is: " + scoreNo);

    }

    public void onClick(View view){

        if(view.getId() == button1.getId()){
            if(rn1>rn2){
                Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT).show();
                scoreNo++;
                RandomNo();

            }
            else if(rn1==rn2){
                Toast.makeText(this, "Both numbers are equal", Toast.LENGTH_SHORT).show();
                RandomNo();
            }
            else{
                Toast.makeText(this, "You are wrong!", Toast.LENGTH_SHORT).show();
                scoreNo--;
                RandomNo();
            }
        }
        if(view.getId() == button2.getId()){
            if(rn2>rn1){
                Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT).show();
                scoreNo++;
                RandomNo();
            }
            else if(rn2==rn1){
                Toast.makeText(this, "Both numbers are equal", Toast.LENGTH_SHORT).show();
                RandomNo();
            }
            else{
                Toast.makeText(this, "You are wrong!", Toast.LENGTH_SHORT).show();
                scoreNo--;
                RandomNo();

            }
        }

    }
}
        /*

        switch (view.getId()){
            case R.id.button:

            case R.id.button2:
                if(rn1>rn2){
                    Toast.makeText(this, "You are correct1", Toast.LENGTH_SHORT).show();
                    RandomNo();
                }
                else{
                    Toast.makeText(this, "You are wrong!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }

    }

}

*/



