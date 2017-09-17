package com.dmtaiwan.alexander.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";


    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = (Button) findViewById(R.id.button_one);
        buttonTwo = (Button) findViewById(R.id.button_two);
        buttonThree = (Button) findViewById(R.id.button_three);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_one:

                makeData("One");
                sendBroadcast();
                break;
            case R.id.button_two:
                makeData("Two");
                sendBroadcast();
                break;
            case R.id.button_three:
                makeData("Three");
                sendBroadcast();
                break;
        }
    }

    private void makeData(String buttonName) {
        ArrayList<WidgetObject> widgetObjects = new ArrayList<>();
        widgetObjects.add(new WidgetObject("Hello"));
        widgetObjects.add(new WidgetObject("From"));
        widgetObjects.add(new WidgetObject("Button"));
        widgetObjects.add(new WidgetObject(buttonName));
        Gson gson = new Gson();
        String json = gson.toJson(widgetObjects);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SHARED_PREFS_KEY, json).commit();
    }

    private void sendBroadcast() {

        Intent intent = new Intent(this, MyWidgetProvider.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE\"");
        sendBroadcast(intent);
    }
}
