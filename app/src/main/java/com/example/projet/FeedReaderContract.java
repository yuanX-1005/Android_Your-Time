package com.example.projet;

import android.provider.BaseColumns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    //Base de données SQLite (non reussi en fin)
    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "session";
        public static final String COLUMN_NAME_DATEDEBUT = "DateDebut";
        public static final String COLUMN_NAME_DATEFIN = "DateFin";
        public static final String COLUMN_NAME_TITLE = "titre";

    }

}
