package com.example.user.noteapp;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by USER on 03-Aug-18.
 */

public class Note implements Serializable{

    private String title;
    private String content;
    private long date_time;

    public Note(){

    }

    public Note(String title, String content, long date_time) {
        this.title = title;
        this.content = content;
        this.date_time = date_time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate_time(long date_time) {
        this.date_time = date_time;
    }

    public String getTitle() {

        return title;
    }

    public String getContent() {
        return content;
    }

    public long getDate_time() {
        return date_time;
    }

    public String getDateTimeFormatted(Context context){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy . hh:mm:ss aa",
        context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(new Date(date_time));
    }
}
