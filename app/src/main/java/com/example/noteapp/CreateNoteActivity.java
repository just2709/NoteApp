package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class CreateNoteActivity extends AppCompatActivity {
    private String selectedColor;
     View viewSubtitle;
    ImageView imagecolor1,imagecolor2,imagecolor3,imagecolor4,imagecolor5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        viewSubtitle = findViewById(R.id.viewSubtitleIndicator);

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        selectedColor = "#333333";

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

    }


    private void setSubtitleColor(){
        GradientDrawable gradientDrawable = (GradientDrawable) viewSubtitle.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));
    }
}
