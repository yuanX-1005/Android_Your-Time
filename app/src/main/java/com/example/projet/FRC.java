package com.example.projet;

import android.provider.BaseColumns;

public class FRC { //RFC = FeedReaderContract
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FRC() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String T_SESS = "Session";
        public static final String C_SESS_DEBUT = "DateDebut";
        public static final String C_SESS_FIN = "DateFin";

        public static final String T_SEQ = "Sequence";
        public static final String C_SEQ_NOM = "SeqNom";
        public static final String C_SEQ_DUREETOTAL = "DureeTotal";
        public static final String C_SEQ_IMAGE = "CheminImage";

        public static final String T_ETAPE = "Etape";
        public static final String C_ETAPE_NOM = "EtapeNom";
        public static final String C_ETAPE_DUREE = "EtapeDuree";
        public static final String C_ETAPE_NREPETER = "NombreRepeter";
        public static final String C_ETAPE_IDAUDIO = "DateFin";
        public static final String C_ETAPE_ORDRE = "DateDebut";
        public static final String C_ETAPE_IDSEQUENCE = "DateFin";

        public static final String T_AUDIO = "audio";
        public static final String C_AUDIO_NOM = "DateDebut";
        public static final String C_AUDIO_DUREE = "DateFin";

        public static final String T_PARAM = "parametre";
        public static final String C_PARAM_IMAGE = "DateDebut";
        public static final String C_PARAM_THEME = "DateFin";

        public static final String T_CREATESESS="";
        public static final String C_CS_IDSEQ = "IdSequence";
        public static final String C_CS_IDSess = "IdSequence";
        public static final String C_CS_DATEEXC = "IdSequence";

        public static final String SEQ_ID = "SeqID";
        public static final String SESS_ID = "";
    }

}
