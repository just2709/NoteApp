package com.example.noteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateNoteActivity extends AppCompatActivity {
    private AlertDialog dialogDeleteNote;
    private String selectedColor;
    View viewSubtitle;
    ImageView imagecolor1,imagecolor2,imagecolor3,imagecolor4,imagecolor5, imageSave;
    EditText inputNoteTitle, inputNoteSubTitle, inputNote, imageNote;
    TextView textDateTime;

    private TextView textWebURL;
    private Note alreadyAvailableNote;
    private LinearLayout layoutWebURL;
    private AlertDialog dialogAddURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        inputNoteTitle = (EditText)findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = (EditText)findViewById(R.id.inputNoteSubTitle);
        inputNote = (EditText)findViewById(R.id.inputNote);
        textDateTime = (TextView) findViewById(R.id.textDateTime);
        imageNote = (EditText)findViewById(R.id.imageNote);
        textDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));
        viewSubtitle = findViewById(R.id.viewSubtitleIndicator);

        imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validate = Validate(inputNoteTitle.getText().toString());

                if (validate.equals("")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    Note note1 = new Note(10,  "23", inputNoteTitle.getText().toString(),
                            inputNoteSubTitle.getText().toString(), inputNote.getText().toString(),
                            textDateTime.getText().toString(), selectedColor, textWebURL.getText().toString());
                    bundle.putSerializable("note", note1);
                    intent.putExtras(bundle);
                    setResult(200, intent);
                    finish();
                } else {
                    Toast.makeText(CreateNoteActivity.this, validate, Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageView imageBack = findViewById(R.id.imageBack);
         textWebURL = findViewById(R.id.textWebURL);
         layoutWebURL = findViewById(R.id.layoutWebUrL);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        selectedColor = "#333333";

        if(getIntent().getBooleanExtra("isViewOrUpdate", false)){
            alreadyAvailableNote = (Note) getIntent().getSerializableExtra("note");
            setViewOrUpdateNote();
        }
        initMiscellaneous();
        setSubtitleColor();
    }

    private void initMiscellaneous(){
        LinearLayout layoutMiscellaneous = findViewById(R.id.layoutMiscellaneous);
        BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


         imagecolor1 = layoutMiscellaneous.findViewById(R.id.imageColor1);
         imagecolor2 = layoutMiscellaneous.findViewById(R.id.imageColor2);
         imagecolor3 = layoutMiscellaneous.findViewById(R.id.imageColor3);
         imagecolor4 = layoutMiscellaneous.findViewById(R.id.imageColor4);
         imagecolor5 = layoutMiscellaneous.findViewById(R.id.imageColor5);

        layoutMiscellaneous.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor ="#333333";
                imagecolor1.setImageResource(R.drawable.icon_done);
                imagecolor2.setImageResource(0);
                imagecolor3.setImageResource(0);
                imagecolor4.setImageResource(0);
                imagecolor5.setImageResource(0);
                setSubtitleColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor ="#FDBE3B";
                imagecolor1.setImageResource(0);
                imagecolor2.setImageResource(R.drawable.icon_done);
                imagecolor3.setImageResource(0);
                imagecolor4.setImageResource(0);
                imagecolor5.setImageResource(0);
                setSubtitleColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor ="#FF4842";
                imagecolor1.setImageResource(0);
                imagecolor2.setImageResource(0);
                imagecolor3.setImageResource(R.drawable.icon_done);
                imagecolor4.setImageResource(0);
                imagecolor5.setImageResource(0);
                setSubtitleColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor ="#3A52FC";
                imagecolor1.setImageResource(0);
                imagecolor2.setImageResource(0);
                imagecolor3.setImageResource(0);
                imagecolor4.setImageResource(R.drawable.icon_done);
                imagecolor5.setImageResource(0);
                setSubtitleColor();
            }
        });
        layoutMiscellaneous.findViewById(R.id.viewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedColor ="#000000";
                imagecolor1.setImageResource(0);
                imagecolor2.setImageResource(0);
                imagecolor3.setImageResource(0);
                imagecolor4.setImageResource(0);
                imagecolor5.setImageResource(R.drawable.icon_done);
                setSubtitleColor();
            }
        });

        layoutMiscellaneous.findViewById(R.id.layoutAddUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                showAddURLDialog();
            }
        });

        layoutMiscellaneous.findViewById(R.id.layoutDeleteNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                showDeleteNoteDialog();
            }
        });

    }


    private void setSubtitleColor(){
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitle.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));
    }
    private void showDeleteNoteDialog() {

        if(dialogDeleteNote == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_note, (ViewGroup) findViewById(R.id.layoutDeleteNoteContainer));
            builder.setView(view);
            dialogDeleteNote = builder.create();
            if(dialogDeleteNote.getWindow() != null) {
                dialogDeleteNote.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            view.findViewById(R.id.btnDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    setResult(199, intent);
                    finish();
                    layoutWebURL.setVisibility(View.VISIBLE);
                    dialogDeleteNote.dismiss();
                }
            });

            view.findViewById(R.id.btnCancelDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDeleteNote.dismiss();
                }
            });
        }

        dialogDeleteNote.show();
    }

    private String Validate(String Title) {
        if (Title.equals("")) {
            return "Chưa nhập tiêu đề";
        }
        return "";
    }

    private void setViewOrUpdateNote() {
        inputNoteTitle.setText(alreadyAvailableNote.getTitle());
        inputNoteSubTitle.setText(alreadyAvailableNote.getSubTitle());
        inputNote.setText(alreadyAvailableNote.getContent());
        textDateTime.setText(alreadyAvailableNote.getDateTime());
//        if(alreadyAvailableNote.getImage() != null && !alreadyAvailableNote.getImage().trim().isEmpty()) {
//            imageNote.setImageBitmap()
//        }
    }

    private void showAddURLDialog() {
        if(dialogAddURL == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_add_url, (ViewGroup) findViewById(R.id.layoutDeleteNoteContainer));
            builder.setView(view);

            dialogAddURL = builder.create();
            if(dialogAddURL.getWindow() != null) {
                dialogAddURL.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            final EditText inputURL  = view.findViewById(R.id.inputURL);
            inputURL.requestFocus();

            view.findViewById(R.id.textAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(inputURL.getText().toString().trim().isEmpty()) {
                        Toast.makeText(CreateNoteActivity.this, "Enter URL", Toast.LENGTH_SHORT).show();
                    } else {
                        textWebURL.setText(inputURL.getText().toString());
                        layoutWebURL.setVisibility(View.VISIBLE);
                        dialogAddURL.dismiss();
                    }
                }
            });

            view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogAddURL.dismiss();
                }
            });
        }

        dialogAddURL.show();
    }
}
