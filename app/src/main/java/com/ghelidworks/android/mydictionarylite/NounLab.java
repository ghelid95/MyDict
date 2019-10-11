package com.ghelidworks.android.mydictionarylite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.ghelidworks.android.mydictionarylite.database.NounBaseHelper;
import com.ghelidworks.android.mydictionarylite.database.NounCursorWraper;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.NounTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class NounLab {
    private static NounLab sNounLab;
    private static final String TAG = "usingApp";
    private Context mContext;
    private Cursor mCursor;
    private SQLiteDatabase mDatabase;

    public static NounLab get(Context context){
        if(sNounLab == null){
            sNounLab = new NounLab(context);
        }
        return sNounLab;
    }
    private NounLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new NounBaseHelper(mContext).getWritableDatabase();
    }

    public void addNoun(Noun n){
            ContentValues values = getContentValues(n);
            mDatabase.insert(NounTable.NAME, null, values);
    }

    public void delNoun(UUID id){
        Log.d(TAG, "entered" + id.toString());
        String nounId = id.toString();
        mDatabase.delete(NounTable.NAME, NounTable.Cols.UUID+"=?",new String[]{nounId});
        Log.d(TAG, "Deleted" + id.toString());
    }

    public List<Noun> getNoun(){
        List<Noun> nouns = new ArrayList<>();

        NounCursorWraper cursor = queryNouns(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                if(cursor.getNoun().getWord() == null) {
                    delNoun(cursor.getNoun().getId());
                    cursor.moveToNext();

                }else {
                    nouns.add(cursor.getNoun());
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            nouns.sort(Comparator.comparing(Noun::getWord));
            return nouns;
        } else{
            return nouns;
        }
    }


    public Cursor search (String searchString) {
        String[] columns = new String[]{NounTable.Cols.WORD};
        searchString = "%" + searchString + "%";
        String where = NounTable.Cols.WORD + " LIKE ?";
        String[]whereArgs = new String[]{searchString};
        Cursor cursor = null;
        cursor = mDatabase.query(NounTable.NAME, columns, where, whereArgs, null, null, null);
        return cursor;
    }

    public Noun getNoun(UUID id){
        NounCursorWraper cursor = queryNouns(
                NounTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try{
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getNoun();
        } finally {
            cursor.close();
        }
    }

    public void updateNoun (Noun noun, boolean update){
        mCursor = search(noun.getWord());
        if(mCursor != null & mCursor.getCount() > 0 & !update){
            delNoun(noun.getId());
        }else {
            String uuidString = noun.getId().toString();
            ContentValues values = getContentValues(noun);
            mDatabase.update(NounTable.NAME, values,
                    NounTable.Cols.UUID + " = ?",
                    new String[]{uuidString});
        }


    }

    private NounCursorWraper queryNouns (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                NounTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new NounCursorWraper(cursor);


    }

    private static ContentValues getContentValues(Noun noun){
        ContentValues values = new ContentValues();
        values.put(NounTable.Cols.UUID, noun.getId().toString());
        values.put(NounTable.Cols.WORD, noun.getWord());
        values.put(NounTable.Cols.TRANSLATE, noun.getTranslate());
        values.put(NounTable.Cols.TYPE, noun.getType());
        values.put(NounTable.Cols.PLURAL, noun.getPlural());
        return values;
    }

}
