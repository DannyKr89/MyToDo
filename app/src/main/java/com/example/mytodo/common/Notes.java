package com.example.mytodo.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes implements Parcelable {

    private int id;

    private static int count = 0;

    private final int color;
    private String title;
    private String description;
    private final String date;


    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Notes(Parcel in) {
        id = in.readInt();
        color = in.readInt();
        title = in.readString();
        description = in.readString();
        date = in.toString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public Notes() {
        this.id = count++;
        this.color = new Colors().getRandomColor();
        this.date = new SimpleDateFormat("HH:mm:ss  dd.MM.yy").format(new Date());
    }

    public Notes(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(color);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
    }

}
