package com.example.mytodo.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Notes implements Parcelable {

    private int id;

    private final int[] idColors = {
            0xFFE91E63,
            0xFF9C27B0,
            0xFFF44336,
            0xFF673AB7,
            0xFF3F51B5,
            0xFF2196F3,
            0xFF03A9F4,
            0xFF00BCD4,
            0xFF009688,
            0xFF4CAF50,
            0xFF8BC34A,
            0xFFCDDC39,
            0xFFFFEB3B,
            0xFFFFC107,
            0xFFFF9800,
            0xFFFF5722,
    };
    private Random random = new Random();

    private static int count = 0;

    private int color;
    private String title, description, date;
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss  dd.MM.yy");
    private Date newDate = new Date();


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
        this.color = idColors[random.nextInt(idColors.length)];
        this.id = count++;
        this.date = formatter.format(newDate);
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
