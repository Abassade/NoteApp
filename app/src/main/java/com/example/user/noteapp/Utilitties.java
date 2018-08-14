package com.example.user.noteapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by USER on 03-Aug-18.
 */

public class Utilitties {

    public static String file_ext = ".bin";

    public static boolean saveNote(Context context, Note note){

        String filename = String.valueOf(note.getDate_time()+file_ext);

        FileOutputStream fileOutputStream;
        ObjectOutput objectOutput;

        try {
            fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            objectOutput  = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(note);
            fileOutputStream.close();
            objectOutput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public static ArrayList<Note> getAllSavedNote(Context context){

        ArrayList<Note> notes = new ArrayList<>();
        File fileDir = context.getFilesDir();
        ArrayList<String> note_list = new ArrayList<>();

        for (String file : fileDir.list()){

            if (file.endsWith(file_ext)){
            note_list.add(file);
            }
        }
        FileInputStream fileInputStream;
        ObjectInput objectInput;

        for (int i=0; i<note_list.size(); i++){

            try {

                fileInputStream = context.openFileInput(note_list.get(i));
                objectInput = new ObjectInputStream(fileInputStream);
                notes.add((Note) objectInput.readObject());
                fileInputStream.close();
                objectInput.close();
            }
            catch (IOException | ClassNotFoundException e){

                e.printStackTrace();
                return null;
            }
        }
    return notes;

    }

    public static Note getNoteByName (Context context, String myfilename){
        File filedir = context.getFilesDir();

        File file = new File(filedir, myfilename);
        Note note;
        if (file.exists()){

            FileInputStream fis ;
            ObjectInputStream ois;

            try{

                fis = context.openFileInput(myfilename);
                ois = new ObjectInputStream(fis);
                note = (Note)ois.readObject();
                fis.close();
                ois.close();

            }  catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return note;
        }
             return null;
    }

    public static void deleteNote(Context context, String dfilename) {

        File file = context.getFilesDir();
        File mfile = new File(file, dfilename);

        if (mfile.exists()){

            mfile.delete();
        }
    }
}
