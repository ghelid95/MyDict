package com.ghelidworks.android.mydictionarylite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.NounTable;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable.VerbInfinitivTable;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable.VerbPatrizipTable;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable.VerbPrateritumTable;

public class NounBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    public static final String DATABASE_NAME = "nounBase.db";

    public NounBaseHelper(Context context){ super(context, DATABASE_NAME, null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + NounTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                NounTable.Cols.UUID + ", " +
                NounTable.Cols.WORD + ", " +
                NounTable.Cols.TYPE + ", " +
                NounTable.Cols.TRANSLATE + ", " +
                NounTable.Cols.PLURAL +
                ")"
        );

        db.execSQL("create table " + VerbTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                VerbTable.Cols.UUID + ", " +
                VerbTable.Cols.WORD + ", " +
                VerbTable.Cols.TYPE + ", " +
                VerbTable.Cols.TRANSLATE +
                ")"
        );
        db.execSQL("create table " + VerbInfinitivTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                VerbInfinitivTable.Cols.ICH + ", " +
                VerbInfinitivTable.Cols.DU + ", " +
                VerbInfinitivTable.Cols.ER_SIE_ES + ", " +
                VerbInfinitivTable.Cols.WIR + ", " +
                VerbInfinitivTable.Cols.IHR + ", " +
                VerbInfinitivTable.Cols.SIE + ", " +
                VerbInfinitivTable.Cols.WERB_UUID + ", " +
                VerbInfinitivTable.Cols.CSIE +
                ")"
        );

        db.execSQL("create table " + VerbPatrizipTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                VerbPatrizipTable.Cols.ICH + ", " +
                VerbPatrizipTable.Cols.DU + ", " +
                VerbPatrizipTable.Cols.ER_SIE_ES + ", " +
                VerbPatrizipTable.Cols.WIR + ", " +
                VerbPatrizipTable.Cols.IHR + ", " +
                VerbPatrizipTable.Cols.SIE + ", " +
                VerbPatrizipTable.Cols.WERB_UUID + ", " +
                VerbPatrizipTable.Cols.CSIE +
                ")"
        );

        db.execSQL("create table " + VerbPrateritumTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                VerbPrateritumTable.Cols.ICH + ", " +
                VerbPrateritumTable.Cols.DU + ", " +
                VerbPrateritumTable.Cols.ER_SIE_ES + ", " +
                VerbPrateritumTable.Cols.WIR + ", " +
                VerbPrateritumTable.Cols.IHR + ", " +
                VerbPrateritumTable.Cols.SIE + ", " +
                VerbPrateritumTable.Cols.WERB_UUID + ", " +
                VerbPrateritumTable.Cols.CSIE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(newVersion>oldVersion){
            //db.execSQL("ALTER TABLE nouns ADD COLUMN plural TEXT DEFAULT ''");
            db.execSQL("create table " + VerbTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    VerbTable.Cols.UUID + ", " +
                    VerbTable.Cols.WORD + ", " +
                    VerbTable.Cols.TYPE + ", " +
                    VerbTable.Cols.TRANSLATE +
                    ")"
            );
            db.execSQL("create table " + VerbInfinitivTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    VerbInfinitivTable.Cols.ICH + ", " +
                    VerbInfinitivTable.Cols.DU + ", " +
                    VerbInfinitivTable.Cols.ER_SIE_ES + ", " +
                    VerbInfinitivTable.Cols.WIR + ", " +
                    VerbInfinitivTable.Cols.IHR + ", " +
                    VerbInfinitivTable.Cols.SIE + ", " +
                    VerbInfinitivTable.Cols.WERB_UUID + ", " +
                    VerbInfinitivTable.Cols.CSIE +
                    ")"
            );

            db.execSQL("create table " + VerbPatrizipTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    VerbPatrizipTable.Cols.ICH + ", " +
                    VerbPatrizipTable.Cols.DU + ", " +
                    VerbPatrizipTable.Cols.ER_SIE_ES + ", " +
                    VerbPatrizipTable.Cols.WIR + ", " +
                    VerbPatrizipTable.Cols.IHR + ", " +
                    VerbPatrizipTable.Cols.SIE + ", " +
                    VerbPatrizipTable.Cols.WERB_UUID + ", " +
                    VerbPatrizipTable.Cols.CSIE +
                    ")"
            );

            db.execSQL("create table " + VerbPrateritumTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    VerbPrateritumTable.Cols.ICH + ", " +
                    VerbPrateritumTable.Cols.DU + ", " +
                    VerbPrateritumTable.Cols.ER_SIE_ES + ", " +
                    VerbPrateritumTable.Cols.WIR + ", " +
                    VerbPrateritumTable.Cols.IHR + ", " +
                    VerbPrateritumTable.Cols.SIE + ", " +
                    VerbPrateritumTable.Cols.WERB_UUID + ", " +
                    VerbPrateritumTable.Cols.CSIE +
                    ")"
            );
        }

    }
}
