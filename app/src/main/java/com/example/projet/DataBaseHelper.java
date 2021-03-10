package com.example.projet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bdYourTime.db";

    private Context mContext;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SESSION);
        //返回建表成功信息
        Toast.makeText(mContext,"Creat Data succeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SESSION);
        onCreate(db);
    }

    //Pas nécessaire
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_CREATE_SESSION =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_SESSION + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATEDEBUT + " INTEGER," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DATEFIN + " INTEGER)";

    private static final String SQL_DELETE_SESSION =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_SESSION;


}
