package com.example.projet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
        db.execSQL(SQL_CREATE_SESS);
        db.execSQL(SQL_CREATE_SESS);
        db.execSQL(SQL_CREATE_SESS);
        db.execSQL(SQL_CREATE_SESS);
        db.execSQL(SQL_CREATE_SESS);
        //返回建表成功信息
        Log.d("DataBaseInit","execute la creation de bd");
        Toast.makeText(mContext,"Creat Data succeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SESSION);
        onCreate(db);
    }

    //Renouvelle la BD si le version est renouvelle
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_CREATE_SESS =
            "CREATE TABLE " + FRC.FeedEntry.T_SESS + " (" +
                    FRC.FeedEntry.SESS_ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_SESS_DEBUT + " INTEGER," +
                    FRC.FeedEntry.C_SESS_FIN + " INTEGER)";

    private static final String SQL_CREATE_SEQ =
            "CREATE TABLE " + FRC.FeedEntry.T_SEQ + " (" +
                    FRC.FeedEntry.SEQ_ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_SEQ_NOM + " INTEGER," +
                    FRC.FeedEntry.C_SEQ_DUREETOTAL + " INTEGER," +
                    FRC.FeedEntry.C_SEQ_IMAGE + " INTEGER)";

    private static final String SQL_CREATE_ETAPE =
            "CREATE TABLE " + FRC.FeedEntry.T_ETAPE + " (" +
                    FRC.FeedEntry.ETAPE_ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_ETAPE_NOM + " INTEGER," +
                    FRC.FeedEntry.C_ETAPE_DUREE + " INTEGER" +
                    FRC.FeedEntry.C_ETAPE_NREPETER + " INTEGER" +
                    FRC.FeedEntry.C_ETAPE_ORDRE + " INTEGER" +
                    FRC.FeedEntry.C_ETAPE_IDAUDIO + " INTEGER" +
                    FRC.FeedEntry.C_ETAPE_IDSEQUENCE + " INTEGER)";

    private static final String SQL_CREATE_PARAM =
            "CREATE TABLE " + FRC.FeedEntry.T_PARAM + " (" +
                    FRC.FeedEntry.PARAM_ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_PARAM_IMAGE + " INTEGER," +
                    FRC.FeedEntry.C_PARAM_THEME + " INTEGER)";

    private static final String SQL_CREATE_CREATESESS =
            "CREATE TABLE " + FRC.FeedEntry.T_CREATESESS + " (" +
                    FRC.FeedEntry.CREATESESS_ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_CS_DATEEXC + " INTEGER," +
                    FRC.FeedEntry.C_CS_IDSEQ + " INTEGER,"  +
                    FRC.FeedEntry.C_CS_IDSess + " INTEGER)";



    private static final String SQL_DELETE_SESSION =
            "DROP TABLE IF EXISTS " + FRC.FeedEntry.T_SESS;



    private static final String SQL_CREATE_SEQUENCE =
            "CREATE TABLE " + FRC.FeedEntry.T_SESS + " (" +
                    FRC.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FRC.FeedEntry.C_SESS_DEBUT + " INTEGER," +
                    FRC.FeedEntry.C_SESS_FIN + " INTEGER)";
}
