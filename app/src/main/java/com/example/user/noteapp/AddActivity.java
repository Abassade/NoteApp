package com.example.user.noteapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private LinearLayout parent;
    private EditText Edtitle, Edbody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        parent = findViewById(R.id.parent);
        Edtitle = findViewById(R.id.title);
        Edbody = findViewById(R.id.body);




        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert methodManager != null;
                methodManager.hideSoftInputFromWindow(Edbody.getWindowToken(), 0);
                methodManager.hideSoftInputFromWindow(Edtitle.getWindowToken(), 1);

            }
        });


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
                Toast.makeText(getBaseContext(), "Note added", Toast.LENGTH_SHORT).show();
                nextActivity();
                break;
            case R.id.lock_note:
                Toast.makeText(getBaseContext(), "Note locked", Toast.LENGTH_SHORT).show();
                nextActivity();
                break;
            case R.id.delete_note:
                Toast.makeText(getBaseContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                nextActivity();
                break;
                default:return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void nextActivity(){
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
