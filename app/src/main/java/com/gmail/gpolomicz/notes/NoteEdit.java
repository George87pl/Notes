package com.gmail.gpolomicz.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class NoteEdit extends AppCompatActivity {
    private static final String TAG = "DEBUGOWANIE";

    EditText editor;
    int id;
    String text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        editor = findViewById(R.id.editorEditText);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        text = intent.getStringExtra("note");
        editor.setText(text);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        
        MainActivity.notesArrayList.set(id, editor.getText().toString());
        MainActivity.adapter.notifyDataSetChanged();

        try {
            String arraySerialized = ObjectSerializer.serialize(MainActivity.notesArrayList);

            SharedPreferences sharedPreferences = getSharedPreferences("com.gmail.gpolomicz.notes", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("save", arraySerialized).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
