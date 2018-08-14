package com.example.user.noteapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 03-Aug-18.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(@NonNull Context context, int resource, ArrayList<Note> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);

        if(convertView==null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, null);
        }
        Note note = getItem(position);

        if(note!=null){

            TextView title = convertView.findViewById(R.id.title_item);
            TextView body = convertView.findViewById(R.id.body_item);
            TextView date_item = convertView.findViewById(R.id.date_time_item);

            title.setText(note.getTitle());
            date_item.setText(note.getDateTimeFormatted(getContext()));

            if(note.getContent().length()>50){

                body.setText(note.getContent().substring(0, 47)+"...");
            }else {

                body.setText(note.getContent());
            }
        }
        return convertView;
    }
}
