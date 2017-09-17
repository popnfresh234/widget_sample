package com.dmtaiwan.alexander.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexander on 9/17/2017.
 */

public class WidgetObject implements Parcelable {
    private String widgetString;

    public WidgetObject(String widgetString) {
        this.widgetString = widgetString;
    }

    public String getWidgetString() {
        return widgetString;
    }

    public void setWidgetString(String widgetString) {
        this.widgetString = widgetString;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.widgetString);
    }

    protected WidgetObject(Parcel in) {
        this.widgetString = in.readString();
    }

    public static final Parcelable.Creator<WidgetObject> CREATOR = new Parcelable.Creator<WidgetObject>() {
        @Override
        public WidgetObject createFromParcel(Parcel source) {
            return new WidgetObject(source);
        }

        @Override
        public WidgetObject[] newArray(int size) {
            return new WidgetObject[size];
        }
    };
}