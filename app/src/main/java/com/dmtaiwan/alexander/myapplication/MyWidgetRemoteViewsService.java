package com.dmtaiwan.alexander.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Alexander on 9/17/2017.
 */

public class MyWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());
    }

    class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context context;
        private ArrayList<WidgetObject> myObjects;

        public MyWidgetRemoteViewsFactory(Context context) {
            this.context = context;

            myObjects = new ArrayList<>();
            myObjects.add(new WidgetObject("This"));
            myObjects.add(new WidgetObject("is"));
            myObjects.add(new WidgetObject("default"));
            myObjects.add(new WidgetObject("data"));


        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(MainActivity.SHARED_PREFS_KEY, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                myObjects = gson.fromJson(json, new TypeToken<ArrayList<WidgetObject>>() {
                }.getType());
            }
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (myObjects != null) {
                return myObjects.size();
            } else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
            rv.setTextViewText(R.id.widget_text_view, myObjects.get(position).getWidgetString());
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
