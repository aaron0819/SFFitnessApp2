package com.example.ddkn.sffitnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.echo.holographlibrary.*;

import java.util.ArrayList;


public class Home extends ActionBarActivity implements AccelerometerListener {


    private long currentTime;
    private int stepCount = 0;
    private long systemTime;
    private ArrayList<Bar> points = new ArrayList<Bar>();
    private int stepsToday = 3238;
    private int monthlySteps = 20422;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bar d = new Bar();
        d.setColor(Color.parseColor("#99CC00"));
        d.setName("7/19/2015");
        d.setValue(7902);
        points.add(d);

        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#FFBB33"));
        d2.setName("7/21/2015");
        d2.setValue(9282);
        points.add(d2);

        Bar d3 = new Bar();
        d3.setColor(Color.parseColor("#99CC00"));
        d3.setName("7/22/2015");
        d3.setValue(0);
        points.add(d3);

        Bar d4 = new Bar();
        d4.setColor(Color.parseColor("#FFBB33"));
        d4.setName("7/23/2015");
        d4.setValue(0);
        points.add(d4);

        Bar d5 = new Bar();
        d5.setColor(Color.parseColor("#99CC00"));
        d5.setName("7/24/2015");
        d5.setValue(0);
        points.add(d5);

        Bar d6 = new Bar();
        d6.setColor(Color.parseColor("#FFBB33"));
        d6.setName("7/25/2015");
        d6.setValue(0);
        points.add(d6);

        Bar d7 = new Bar();
        d7.setColor(Color.parseColor("#99CC00"));
        d7.setName("7/26/2015");
        d7.setValue(0);
        points.add(d7);


        BarGraph g = (BarGraph)findViewById(R.id.graph);
        g.setBars(points);

        Button menuButton = (Button) findViewById(R.id.button3);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Menu2.class));
            }
        });
    }

    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub

    }

    public void onShake(float force) {
        systemTime = System.currentTimeMillis();

        TextView textView = (TextView) findViewById(R.id.stepCountTextView);
        TextView textview2 = (TextView) findViewById(R.id.stepCountMonthTextView);

        if (systemTime + 10000 > currentTime) {
            stepsToday++;
            currentTime = System.currentTimeMillis();
            monthlySteps++;
        }

        textView.setText("Steps Today: " + stepsToday);
        textview2.setText("Steps This Month: " + monthlySteps);

    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getBaseContext(), "onResume Accelerometer Started",
        //         Toast.LENGTH_SHORT).show();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(this)) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            //   Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped",
            //
            //          Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  destroy");

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            // Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stopped",
            //Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

