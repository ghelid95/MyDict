package com.ghelidworks.android.mydictionarylite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import com.ghelidworks.android.mydictionarylite.Noun;
import com.ghelidworks.android.mydictionarylite.NounLab;
import com.ghelidworks.android.mydictionarylite.Verb;
import com.ghelidworks.android.mydictionarylite.database.NounBaseHelper;
import com.ghelidworks.android.mydictionarylite.database.NounCursorWraper;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class VerbLab {
    private static VerbLab sVerbLab;
    private static final String TAG = "usingApp";
    private Context mContext;
    private Cursor mCursor;
    private SQLiteDatabase mDatabase;

    public static VerbLab get(Context context){
        if(sVerbLab == null){
            sVerbLab = new VerbLab(context);
        }
        return sVerbLab;
    }
    private VerbLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new NounBaseHelper(mContext).getWritableDatabase();
    }

    public void addVerb(Verb v){
        ContentValues values = getContentValues(v);
        mDatabase.insert(VerbTable.NAME, null, values);
        ContentValues infValues = getInfinitivValues(v);
        mDatabase.insert(VerbTable.VerbInfinitivTable.NAME, null, infValues);
        ContentValues partrizipValues = getPartrizipValues(v);
        mDatabase.insert(VerbTable.VerbPatrizipTable.NAME, null, partrizipValues);
        ContentValues prateritumValues = getPrateritumValues(v);
        mDatabase.insert(VerbTable.VerbPrateritumTable.NAME, null, prateritumValues);
    }

    public void delVerb(UUID id){
        Log.d(TAG, "entered" + id.toString());
        String nounId = id.toString();
        mDatabase.delete(NounDbSchema.VerbTable.NAME, NounDbSchema.NounTable.Cols.UUID+"=?",new String[]{nounId});
        mDatabase.delete(VerbTable.VerbPrateritumTable.NAME, NounDbSchema.NounTable.Cols.UUID+"=?",new String[]{nounId});
        mDatabase.delete(VerbTable.VerbInfinitivTable.NAME, NounDbSchema.NounTable.Cols.UUID+"=?",new String[]{nounId});
        mDatabase.delete(VerbTable.VerbPatrizipTable.NAME, NounDbSchema.NounTable.Cols.UUID+"=?",new String[]{nounId});
        Log.d(TAG, "Deleted" + id.toString());
    }

    public List<Verb> getVerb(){
        List<Verb> verbs = new ArrayList<>();
        int index = 0;
        NounCursorWraper cursor = queryVerbs(null, null);
        NounCursorWraper infCurs = queryInfVerbs(null,null);
        NounCursorWraper praterCurs = queryPraterVerbs(null,null);
        NounCursorWraper pratriCurs = queryPratriVerbs(null,null);

        try{
            cursor.moveToFirst();
            infCurs.moveToFirst();
            praterCurs.moveToFirst();
            pratriCurs.moveToFirst();
            while (!cursor.isAfterLast()){
                verbs.add(cursor.getVerb());
                verbs.set(index,infCurs.getInfVerb(verbs.get(index)));
                verbs.set(index,praterCurs.getPraterVerb(verbs.get(index)));
                verbs.set(index,pratriCurs.getPatrizipVerb(verbs.get(index)));
                cursor.moveToNext();
                infCurs.moveToNext();
                index++;
            }

        } finally {
            cursor.close();
        }
        return verbs;
    }


    public Cursor search (String searchString) {
        String[] columns = new String[]{VerbTable.Cols.WORD};
        searchString = "%" + searchString + "%";
        String where = VerbTable.Cols.WORD + " LIKE ?";
        String[]whereArgs = new String[]{searchString};
        Cursor cursor = null;
        cursor = mDatabase.query(VerbTable.NAME, columns, where, whereArgs, null, null, null);
        return cursor;
    }

    public Verb getVerb(UUID id){
        NounCursorWraper cursor = queryVerbs(
                VerbTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try{
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getVerb();
        } finally {
            cursor.close();
        }
    }

    public void updateVerb (Verb verb, boolean update){
        mCursor = search(verb.getWord());
        if(mCursor != null & mCursor.getCount() > 0 & !update){
            delVerb(verb.getId());
        }else {
            String uuidString = verb.getId().toString();
            ContentValues values = getContentValues(verb);
            mDatabase.update(VerbTable.NAME, values,
                    VerbTable.Cols.UUID + " = ?",
                    new String[]{uuidString});
        }


    }

    private NounCursorWraper queryVerbs (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                VerbTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new NounCursorWraper(cursor);


    }

    private NounCursorWraper queryInfVerbs (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                VerbTable.VerbInfinitivTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new NounCursorWraper(cursor);


    }

    private NounCursorWraper queryPraterVerbs (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                VerbTable.VerbPrateritumTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new NounCursorWraper(cursor);


    }

    private NounCursorWraper queryPratriVerbs (String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                VerbTable.VerbPatrizipTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new NounCursorWraper(cursor);


    }

    private static ContentValues getContentValues(Verb verb){
        ContentValues values = new ContentValues();
        values.put(VerbTable.Cols.UUID, verb.getId().toString());
        values.put(VerbTable.Cols.WORD, verb.getWord());
        values.put(VerbTable.Cols.TRANSLATE, verb.getTranslate());
        values.put(VerbTable.Cols.TYPE, verb.getType());
        return values;
    }
    private static ContentValues getInfinitivValues (Verb verb){
        ContentValues values = new ContentValues();
        values.put(VerbTable.VerbInfinitivTable.Cols.WERB_UUID, verb.getId().toString());
        values.put(VerbTable.VerbInfinitivTable.Cols.ICH, verb.getIch(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.DU, verb.getDu(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.ER_SIE_ES, verb.getErSieEs(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.WIR, verb.getWir(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.IHR, verb.getWir(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.SIE, verb.getSie(0));
        values.put(VerbTable.VerbInfinitivTable.Cols.CSIE, verb.getCSie(0));
        return values;
    }
    private static ContentValues getPartrizipValues (Verb verb){
        ContentValues values = new ContentValues();
        values.put(VerbTable.VerbPatrizipTable.Cols.WERB_UUID, verb.getId().toString());
        values.put(VerbTable.VerbPatrizipTable.Cols.ICH, verb.getIch(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.DU, verb.getDu(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.ER_SIE_ES, verb.getErSieEs(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.WIR, verb.getWir(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.IHR, verb.getIhr(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.SIE, verb.getSie(1));
        values.put(VerbTable.VerbPatrizipTable.Cols.CSIE, verb.getCSie(1));
        return values;
    }
    private static ContentValues getPrateritumValues (Verb verb){
        ContentValues values = new ContentValues();
        values.put(VerbTable.VerbPrateritumTable.Cols.WERB_UUID, verb.getId().toString());
        values.put(VerbTable.VerbPrateritumTable.Cols.ICH, verb.getIch(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.DU, verb.getDu(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.ER_SIE_ES, verb.getErSieEs(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.WIR, verb.getWir(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.IHR, verb.getIhr(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.SIE, verb.getSie(2));
        values.put(VerbTable.VerbPrateritumTable.Cols.CSIE, verb.getCSie(2));
        return values;
    }
}
