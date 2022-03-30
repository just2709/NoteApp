package com.example.noteapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {
    //tên bảng
    public static final String TableName = "NoteTable";
    //tên các cột trong bảng
    public static final String Id = "Id";
    public static final String Image = "Image";
    public static final String Title = "Title";
    public static final String subTitle = "subTitle";
    public static final String Content = "Content";
    public static final String DateTime = "DateTime";
    public static final String Color = "Color";
    public static final String WebLink = "WebLink";


    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //tạo câu sql để tạo bảng TableContact
        String sqlCreate = "Create table if not exists " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT, "
                + Image + " Text, "
                + Title + " Text, "
                + subTitle + " Text, "
                + Content + " Text,"
                + DateTime + " Text, "
                + Color + " Text,"
                + WebLink + " Text)";
        //chạy câu truy vấn SQL để tạo bảng
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xóa bảng TableContact đã có
        db.execSQL("Drop table if exists " + TableName);
        //tạo lại
        onCreate(db);
    }
    //hàm thêm một contact vào bảng TableContact
    public void addNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
//       value.put(Id,  note.getId());
        value.put(Image, note.getImage());
        value.put(Title, note.getTitle());
        value.put(subTitle, note.getSubTitle());
        value.put(Content, note.getContent());
        value.put(DateTime, note.getDateTime());
        value.put(Color, note.getColor());
        value.put(WebLink, note.getWebLink());
        db.insert(TableName,null, value);
        db.close();
    }
    public void updateNote(int id, Note note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
//     value.put(Id, note.getId());
        value.put(Image, note.getImage());
        value.put(Title, note.getTitle());
        value.put(subTitle, note.getSubTitle());
        value.put(Content, note.getContent());
        value.put(DateTime, note.getDateTime());
        value.put(Color, note.getColor());
        value.put(WebLink, note.getWebLink());
        db.update(TableName, value,Id + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteNote(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where ID=" + id;
        db.execSQL(sql);
//        db.execSQL("Update " + TableName + " SET ID=0 where Name = " + TableName);
        db.close();
    }

    //lay ra id
    @SuppressLint("Range")
    public int getIDNote(int pos)
    {

        String sql = "Select * from " + TableName + " Where id = " + pos ;
//        String sql = "Select *, ROW_NUMBER() AS STT From " + TableName;

        SQLiteDatabase db = this.getReadableDatabase();
        int index = 0;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!=null)
            while (cursor.moveToNext())
            {
                index = cursor.getInt(0);
            }
        return index;
    }

    //lấy tất cả các dòng của bảng TableContact trả về dạng ArrayList
    public ArrayList<Note> getAllNote()
    {
        ArrayList<Note> list = new ArrayList<>();
        //câu truy vấn
        String sql = "Select * from " + TableName;
        //lấy đối tượng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //chạy câu truy vấn trả về dạng Cursor
        Cursor cursor = db.rawQuery(sql,null);
        //tạo ArrayList<Contact> để trả về;
        if(cursor!=null)
            while (cursor.moveToNext())
            {
                Note note = new Note(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                        );
                list.add(note);
            }
        return list;
    }
}
