package com.ghelidworks.android.mydictionarylite.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ghelidworks.android.mydictionarylite.Noun;
import com.ghelidworks.android.mydictionarylite.Verb;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.NounTable;
import com.ghelidworks.android.mydictionarylite.database.NounDbSchema.VerbTable;

import java.util.UUID;

public class NounCursorWraper extends CursorWrapper {
    public NounCursorWraper(Cursor cursor){
        super(cursor);
    }

    public Noun getNoun(){
        String uuidString = getString(getColumnIndex(NounTable.Cols.UUID));
        String word = getString(getColumnIndex(NounTable.Cols.WORD));
        int type = getInt(getColumnIndex(NounTable.Cols.TYPE));
        String translate = getString(getColumnIndex(NounTable.Cols.TRANSLATE));
        String plural = getString(getColumnIndex(NounTable.Cols.PLURAL));

        Noun noun = new Noun(UUID.fromString(uuidString));
        noun.setWord(word);
        noun.setTranslate(translate);
        noun.setType(type);
        noun.setPlural(plural);

        return noun;
    }

    public Verb getVerb(){
        String uuidString = getString(getColumnIndex(VerbTable.Cols.UUID));
        String word = getString(getColumnIndex(VerbTable.Cols.WORD));
        String translate = getString(getColumnIndex(VerbTable.Cols.TRANSLATE));
        int type = getInt(getColumnIndex(VerbTable.Cols.TYPE));


        Verb verb = new Verb(UUID.fromString(uuidString));
        verb.setWord(word);
        verb.setType(type);
        verb.setTranslate(translate);
        return verb;
    }
    public Verb getInfVerb(Verb verb){
        verb.setIch(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.ICH)), 0);
        verb.setDu(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.DU)),0);
        verb.setErSieEs(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.ER_SIE_ES)),0);
        verb.setWir(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.WIR)),0);
        verb.setIhr(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.IHR)),0);
        verb.setSie(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.SIE)),0);
        verb.setCSie(getString(getColumnIndex(VerbTable.VerbInfinitivTable.Cols.CSIE)),0);

        return verb;

    }

    public Verb getPraterVerb(Verb verb){
        verb.setIch(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.ICH)),1);
        verb.setDu(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.DU)),1);
        verb.setErSieEs(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.ER_SIE_ES)),1);
        verb.setWir(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.WIR)),1);
        verb.setIhr(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.IHR)),1);
        verb.setSie(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.SIE)),1);
        verb.setCSie(getString(getColumnIndex(VerbTable.VerbPrateritumTable.Cols.CSIE)),1);

        return verb;
    }

    public Verb getPatrizipVerb(Verb verb){
        verb.setIch(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.ICH)),2);
        verb.setDu(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.DU)),2);
        verb.setErSieEs(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.ER_SIE_ES)),2);
        verb.setWir(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.WIR)),2);
        verb.setIhr(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.IHR)),2);
        verb.setSie(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.SIE)),2);
        verb.setCSie(getString(getColumnIndex(VerbTable.VerbPatrizipTable.Cols.CSIE)),2);

        return verb;
    }
}
