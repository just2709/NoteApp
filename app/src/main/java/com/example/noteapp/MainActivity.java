package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NoteListener {

    private AlertDialog dialogDeleteNote;
    private ImageView imageAddWebLink;
    //public static final int REQUEST_CODE_ADD_NOTE = 1;

    ArrayList<Note> lstNote;
    MyAdapter lstAdapter;
    RecyclerView listView;
    EditText editSearch;
    //public static final int REQUEST_CODE_ADD_NOTE = 1;
    MyDB mysqlitedb;
    int selectedid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);

        listView = findViewById(R.id.notesRecyclerView);
        listView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        editSearch = findViewById(R.id.inputSearch);
        mysqlitedb = new MyDB(this, "NoteAppDB", null, 1);

//        mysqlitedb.addNote(new Note(1, "2", "Công suất 200W", "Hello", "...", "...", "...", "..."));
        lstNote = mysqlitedb.getAllNote();
        lstAdapter = new MyAdapter(lstNote, this);
        listView.setAdapter(lstAdapter);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        //search
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lstAdapter.getFilter().filter(charSequence.toString());
                lstAdapter.notifyDataSetChanged();
                listView.setAdapter(lstAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onNoteClicked(Note note, int position) {
        selectedid = position;
        Intent intent = new Intent(getApplicationContext(), CreateNoteActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, 198);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle;
        bundle = data.getExtras();
        Note note = (Note) bundle.getSerializable("note");
        if(requestCode==100 && resultCode==200)
        {
            //đặt vào listData
            lstNote.add(note);
            mysqlitedb.addNote(note);
        } else if (requestCode==100 && resultCode==199) {
            mysqlitedb.deleteNote(selectedid);
        }
        //cập nhật adapter
        lstNote = mysqlitedb.getAllNote();
        lstAdapter = new MyAdapter(lstNote, this);
        lstAdapter.notifyDataSetChanged();
        listView.setAdapter(lstAdapter);
    }

}