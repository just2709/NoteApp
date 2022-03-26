package com.example.noteapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialogDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void showDeleteNoteDialog() {
        if(dialogDeleteNote == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_delete_note,
                    (ViewGroup) findViewById(R.id.layoutDeleteNoteContainer)
            );
            builder.setView(view);
            dialogDeleteNote = builder.create();
            if(dialogDeleteNote.getWindow() != null)
            {
                dialogDeleteNote.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            view.findViewById(R.id.btnCancelDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            view.findViewById(R.id.btnDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}