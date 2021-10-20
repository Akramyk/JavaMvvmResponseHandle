package com.strides.soft.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d("Act_Lifecycle", "2_Second onCreate()");

        Button nextAct = findViewById(R.id.btn_next_act);
        nextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        //Check Stack Status of Activity
        ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);
        if(taskList.get(0).numActivities == 1 && taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            //Log.i("Act_Lauch", "SecondActivity is last activity in the stack");
            Log.d("Act_Lauch", "1_Main "+taskList.get(0).topActivity.getClassName());
        }
    }


    //1
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Act_Lifecycle", "2_Second onStart()");
    }

    //2
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Act_Lifecycle", "2_Second onResume()");
    }

    //3
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Act_Lifecycle", "2_Second onPause()");
    }

    //4
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Act_Lifecycle", "2_Second onStop()");
    }

    //5
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Act_Lifecycle", "2_Second onRestart()");
    }

    //6
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Act_Lifecycle", "2_Second onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("Act_Lifecycle", "2_Second onNewIntent()");
    }

}