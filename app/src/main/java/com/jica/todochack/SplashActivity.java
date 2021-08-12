package com.jica.todochack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity  extends AppCompatActivity {
    /*
    public static SharedPreferences.Editor editor;
    public static boolean checkFirst;

     */
    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.activity_splash);

        /*
        SharedPreferences sharedPreferences = getSharedPreferences("16", Activity.MODE_PRIVATE);
        checkFirst = sharedPreferences.getBoolean("checkFirst",false);
         */

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                /*
                if(checkFirst == false) {
                    editor = sharedPreferences.edit();
                    Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                    startActivity(intent);
                    editor.putBoolean("checkFirst", true);
                    editor.commit();
                    finish();
                }
                else {
                    startActivity(new Intent(getApplication(), MainActivity.class));
                    SplashActivity.this.finish();

                }
                 */
            }
        },1000); //splash 띄우는 시간 1초

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}