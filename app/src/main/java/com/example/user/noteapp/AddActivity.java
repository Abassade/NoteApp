package com.example.user.noteapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private LinearLayout parent;
    private EditText Edtitle, Edbody;
    private TextView mydate;
    private   String mFilename;
    private Note loadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        parent = findViewById(R.id.parent);
        Edtitle = findViewById(R.id.title);
        Edbody = findViewById(R.id.body);
        mydate = findViewById(R.id.mydate);

       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy . hh:mm:ss aa");
       mydate.setText(String.valueOf(dateFormat.format(new Date())));
        mFilename = getIntent().getStringExtra("file_me");

        if (mFilename!=null){

            loadedNote = Utilitties.getNoteByName(this, mFilename);

            if (loadedNote!=null){

                Edtitle.setText(loadedNote.getTitle());
                Edbody.setText(loadedNote.getContent());
                mydate.setText(String.valueOf(loadedNote.getDateTimeFormatted(this)));

            }

        }

        hideKeyboard();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menu_id = item.getItemId();

        switch (menu_id){

            case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            break;
            case R.id.add_note:
                saveNote();
                break;
            case R.id.lock_note:
                Toast.makeText(getBaseContext(), "Note locked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_note:
               deleteNote();
                break;
                default:return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveNote() {

        if(Edtitle.getText().toString().trim().isEmpty() || Edbody.getText().toString().trim().isEmpty()){

            Toast.makeText(getBaseContext(), "Empty note can't be saved", Toast.LENGTH_SHORT).show();
        }else{

        Note note;
        if (loadedNote==null){

            note = new Note(Edtitle.getText().toString(), Edbody.getText().toString(), System.currentTimeMillis());

        }
        else {

            note = new Note(Edtitle.getText().toString(), Edbody.getText().toString(), loadedNote.getDate_time());

        }

        if(Utilitties.saveNote(this, note)){

            Toast.makeText(getBaseContext(), "Note saved", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getBaseContext(), "Note not saved," +
                    " please ensure you have enough space on your device", Toast.LENGTH_SHORT).show();
        }
        finish();
        }
    }

    private void deleteNote() {

        if (loadedNote==null){

            finish();
        }else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete").setMessage("Do you really want to delete "+Edtitle.getText()+" ?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Utilitties.deleteNote(getApplicationContext(), loadedNote.getDate_time()+Utilitties.file_ext);
                            Toast.makeText(getBaseContext(), Edtitle.getText().toString()+" deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).setIcon(R.drawable.ic_warning_black_24dp);
            dialog.create().show();
        }
    }

    private void hideKeyboard() {

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager methodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                assert methodManager != null;
                methodManager.hideSoftInputFromWindow(Edtitle.getWindowToken(), 0);
                methodManager.hideSoftInputFromWindow(Edbody.getWindowToken(), 1);
            }
        });
    }

}
