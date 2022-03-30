package com.example.noteapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateNoteActivity extends AppCompatActivity {
    private AlertDialog dialogDeleteNote;
    private String selectedColor;
    private String SelectedImagePath;
    View viewSubtitle;
    ImageView imagecolor1,imagecolor2,imagecolor3,imagecolor4,imagecolor5, imageSave;
    EditText inputNoteTitle, inputNoteSubTitle, inputNote;
    TextView textDateTime, tvID;

    private TextView textWebURL;
    private Note alreadyAvailableNote;
    private LinearLayout layoutWebURL;
    private AlertDialog dialogAddURL;
    private ImageView imageNote;
    private String ID;

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private  static final int REQUEST_CODE_SELECT_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        selectedColor = "#333333";
        SelectedImagePath = "";
        initMiscellaneous();

        tvID = findViewById(R.id.tvID);
        inputNoteTitle = (EditText)findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = (EditText)findViewById(R.id.inputNoteSubTitle);
        inputNote = (EditText)findViewById(R.id.inputNote);
        textDateTime = (TextView) findViewById(R.id.textDateTime);
        imageNote = findViewById(R.id.imageNote);
        textDateTime.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date()));
        viewSubtitle = findViewById(R.id.viewSubtitleIndicator);
        textWebURL = findViewById(R.id.textWebURL);
        imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validate = Validate(inputNoteTitle.getText().toString());

                if (validate.equals("")) {
                    Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
//                    bundle.putInt("getSelectedID", alreadyAvailableNote.getId());
                    Note note1 = new Note(SelectedImagePath, inputNoteTitle.getText().toString(),
                            inputNoteSubTitle.getText().toString(), inputNote.getText().toString(),
                            textDateTime.getText().toString(), selectedColor.toString(), textWebURL.getText().toString());
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
        layoutWebURL = findViewById(R.id.layoutWebURL);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                startActivityForResult(intent, 400);
            }
        });

        if(getIntent().getBooleanExtra("isViewOrUpdate", false)){
            alreadyAvailableNote = (Note) getIntent().getSerializableExtra("note");
            setViewOrUpdateNote();
        }
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

        // Làm phần add Image
        layoutMiscellaneous.findViewById(R.id.layoutAddImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if(ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            CreateNoteActivity.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else{
                    selectImage();
                }
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

    // Xét màu
    private void setSubtitleColor(){
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitle.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));
    }

    // Thêm ảnh
    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    // phần thêm ảnh
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri selectedImageUri = data.getData();
                if(selectedImageUri != null){
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageNote.setImageBitmap(bitmap);
                        imageNote.setVisibility(View.VISIBLE);

                        SelectedImagePath = getPathFromUri(selectedImageUri);

                    }catch (Exception exception){
                        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    // này cũng thuộc phần thêm ảnh
    private String getPathFromUri(Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri, null, null, null,null);
        if(cursor == null){
            filePath = contentUri.getPath();
        }else{
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }

    private void showAddURLDialog() {
        if(dialogAddURL == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_add_url, (ViewGroup) findViewById(R.id.layoutAddUrlContainer));
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

    private void showDeleteNoteDialog() {

        if(dialogDeleteNote == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_note, (ViewGroup) findViewById(R.id.layoutWebUrL));
            builder.setView(view);
            dialogDeleteNote = builder.create();
            if(dialogDeleteNote.getWindow() != null) {
                dialogDeleteNote.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            view.findViewById(R.id.btnDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
//                    bundle.putInt("getSelectedID", alreadyAvailableNote.getId());
                    Integer selectID = new Integer(ID);
                    bundle.putInt("selectedID", selectID);
                    intent.putExtras(bundle);
                    setResult(199, intent);
                    finish();
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
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.putInt("getSelectedID", alreadyAvailableNote.getId());

//        Bundle bundle = new Bundle();
//        bundle.putInt("getSelectedID", alreadyAvailableNote.getId());
        ID = String.valueOf(alreadyAvailableNote.getId());
        Toast.makeText(CreateNoteActivity.this, "ID: " + alreadyAvailableNote.getId(), Toast.LENGTH_SHORT).show();
        tvID.setText(ID);
        inputNoteTitle.setText(alreadyAvailableNote.getTitle());
        inputNoteSubTitle.setText(alreadyAvailableNote.getSubTitle());
        inputNote.setText(alreadyAvailableNote.getContent());
        textWebURL.setText(alreadyAvailableNote.getWebLink());
        textDateTime.setText(alreadyAvailableNote.getDateTime());
        if(alreadyAvailableNote.getImage() != ""){
            imageNote.setImageBitmap(BitmapFactory.decodeFile(alreadyAvailableNote.getImage()));
            imageNote.setVisibility(View.VISIBLE);
        }else {
            imageNote.setVisibility(View.GONE);
        }
        selectedColor = alreadyAvailableNote.getColor();
        SelectedImagePath = alreadyAvailableNote.getImage();
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validate = Validate(inputNoteTitle.getText().toString());

                if (validate.equals("")) {
                    Intent intent = new Intent(CreateNoteActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    Integer selectID = new Integer(ID);
                    Note note1 = new Note(SelectedImagePath, inputNoteTitle.getText().toString(),
                            inputNoteSubTitle.getText().toString(), inputNote.getText().toString(),
                            textDateTime.getText().toString(), selectedColor.toString(), textWebURL.getText().toString());
                    bundle.putSerializable("noteUpdate", note1);
                    bundle.putInt("selectedIDUpdate", selectID);
                    intent.putExtras(bundle);
                    setResult(190, intent);
                    finish();
                } else {
                    Toast.makeText(CreateNoteActivity.this, validate, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
