package com.ghelidworks.android.mydictionarylite;

import java.util.UUID;

public class Noun {
    private UUID mId;
    private String mWord;
    private String mTranslate;
    private int mType;
    private String mPlural;

    public String getPlural() {
        return mPlural;
    }

    public void setPlural(String plural) {
        mPlural = plural;
    }

    public UUID getId() {
        return mId;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getTranslate() {
        return mTranslate;
    }

    public void setTranslate(String translate) {
        mTranslate = translate;
    }

    public int getType() {
        return mType;
    }

    public String getTypeText(){
        switch (mType) {
            case (0):
                return "Der";
            case(1):
                return "Das";
            case (2):
                return "Die";
        }
        return "";
    }

    public void setType(int type) {
        mType = type;
    }

    public Noun(){
        this(UUID.randomUUID());
    }
    public Noun(UUID id){
        mId = id;
    }
}
