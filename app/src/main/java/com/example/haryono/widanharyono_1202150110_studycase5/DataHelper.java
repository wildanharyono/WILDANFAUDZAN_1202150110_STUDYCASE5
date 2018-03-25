package com.example.haryono.widanharyono_1202150110_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by haryono on 3/25/2018.
 */

public class DataHelper extends SQLiteOpenHelper  {
    Context context;
    SQLiteDatabase db;

    public static final String DB_NAME = "com.example.haryono.widanharyono_1202150110_studycase5;";
    public static final String TABLE_NAME = "daftartodo";
    public static final String TODO_NAME = "todo";
    public static final String TODO_DESCRIPTION = "description";
    public static final String TODO_PRIORITY = "priority";


    public DataHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+ TABLE_NAME +" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(Data list) {
        ContentValues mValue = new ContentValues();
        mValue.put(TODO_NAME, list.getTodoName());
        mValue.put(TODO_DESCRIPTION, list.getTodoDesc());
        mValue.put(TODO_PRIORITY, list.getTodoPriority());
        long hasil = db.insert(TABLE_NAME, null, mValue);
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    //method untuk menghapus data pada database
    public boolean deletData(String ToDo) {
        return db.delete(TABLE_NAME, TODO_NAME +"=\""+ToDo+"\"", null)>0;
    }

    public void getData(ArrayList<Data> List){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+ TABLE_NAME, null);
        while (cursor.moveToNext()){
            List.add(new Data(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
