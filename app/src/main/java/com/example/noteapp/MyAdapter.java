package com.example.noteapp;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder>{

    private List<Note> notes;
    private NoteListener noteListener;

    public MyAdapter(List<Note> notes, NoteListener noteListener) {

        this.notes = notes;
        this.noteListener = noteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_note,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteListener.onNoteClicked(notes.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textSubtitle, textDateTime;
        LinearLayout layoutNote;
        RoundedImageView imageNote;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            layoutNote = itemView.findViewById(R.id.layoutNote);
        }

        void setNote(Note note){
            textTitle.setText(note.getTitle());
            if (note.getSubTitle().trim().isEmpty()){
                textSubtitle.setVisibility(View.GONE);
            }else{
                textSubtitle.setText(note.getSubTitle());
            }
            textDateTime.setText(note.getDateTime());
//            GradientDrawable gradientDrawable = (GradientDrawable) layoutNote.getBackground();
//            if(note.getColor() != null)
//            {
//                gradientDrawable.setColor(Color.parseColor(note.getColor()));
//            } else {
//                gradientDrawable.setColor(Color.parseColor("#333333"));
//            }

//            GradientDrawable gradientDrawable = (GradientDrawable) layoutNote.getBackground();
//            if(note.getColor() != null)
//            {
//                gradientDrawable.setColor(Color.parseColor(note.getColor()));
//            } else {
//                gradientDrawable.setColor(Color.parseColor("#333333"));
//            }

            // add image
//            if(note.setImage() != null){
//                imageNote.setImageBitmap(BitmapFactory.decodeFile(note.getImage()));
//                imageNote.setVisibility(View.VISIBLE);
//            }else {
//                imageNote.setVisibility(View.GONE);
//            }

        }

    }
}

