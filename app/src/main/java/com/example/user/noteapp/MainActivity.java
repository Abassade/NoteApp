package com.example.user.noteapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton fab_add;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_add = findViewById(R.id.fab_add);
        listView = findViewById(R.id.list_view);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), AddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(null);

        ArrayList<Note> notes = Utilitties.getAllSavedNote(this);

        if(notes==null || notes.size()==0){

            Toast.makeText(getBaseContext(), "No saved note", Toast.LENGTH_SHORT).show();
        }
        else {

            final NoteAdapter adapter = new NoteAdapter(this, R.layout.note_item, notes);
            listView.setAdapter(adapter);

           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   String file_name = ((Note)listView.getItemAtPosition(position)).getDate_time()+Utilitties.file_ext;


                           Intent intent = new Intent(getBaseContext(), ViewActivity.class);
                           intent.putExtra("mFile", file_name);
                           startActivity(intent);

               }
           });
        }
    }
}
