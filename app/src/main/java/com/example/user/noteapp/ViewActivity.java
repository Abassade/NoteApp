package com.example.user.noteapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView date, title, body;
    private Note load;
    String fName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        fName = getIntent().getStringExtra("mFile");

        date =findViewById(R.id.view_date);
        title =findViewById(R.id.view_title);
        body =findViewById(R.id.view_body);
        load = Utilitties.getNoteByName(this, fName);

             date.setText(String.valueOf(load.getDateTimeFormatted(this)));
             title.setText(load.getTitle());
             body.setText(load.getContent());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.edit_note){

            Intent intent = new Intent(getBaseContext(), AddActivity.class);
            intent.putExtra("file_me", fName);
            startActivity(intent);
            finish();

        }
        else if (item.getItemId()==android.R.id.home){

            NavUtils.navigateUpFromSameTask(this);

        }

        return true;
    }
}
