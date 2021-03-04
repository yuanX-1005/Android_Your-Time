package com.example.projet;

import android.provider.BaseColumns;

public class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "session";
        public static final String COLUMN_NAME_DATEDEBUT = "DateDebut";
        public static final String COLUMN_NAME_DATEFIN = "DateFin";
    }

}
