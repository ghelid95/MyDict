package com.ghelidworks.android.mydictionarylite;

import java.util.UUID;

public class Verb {
    private UUID mId;
    private String mWord;
    private String mTranslate;
    private boolean mType;

    public UUID getId() {
        return mId;
    }

    public String getTranslate() {
        return mTranslate;
    }

    public void setTranslate(String translate) {
        mTranslate = translate;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public boolean getType() {
        return mType;
    }

    public void setType(int type) {
        if (type==0){
            mType = false;
        }else{
            mType = true;
        }
    }

    public Verb(){
        this(UUID.randomUUID());
    }
    public Verb(UUID id){
        mId = id;
    }
        private String[] mIch = {"","",""};
        private String[] mDu = {"","",""};
        private String[] mErSieEs = {"","",""};
        private String[] mWir = {"","",""};
        private String[] mIhr = {"","",""};
        private String[] mSie = {"","",""};
        private String[] mCSie = {"","",""};


        public String getIch(int index) {
            return mIch[index];
        }

        public void setIch(String ich, int index) {
            mIch[index] = ich;
        }

        public String getDu(int index) {
            return mDu[index];
        }

        public void setDu(String du, int index) {
            mDu[index] = du;
        }

        public String getErSieEs(int index) {
            return mErSieEs[index];
        }

        public void setErSieEs(String erSieEs, int index) {
            mErSieEs[index] = erSieEs;
        }

        public String getWir(int index) {
            return mWir[index];
        }

        public void setWir(String wir, int index) {
            mWir[index] = wir;
        }

        public String getIhr(int index) {
            return mIhr[index];
        }

        public void setIhr(String ihr, int index) {
            mIhr[index] = ihr;
        }

        public String getSie(int index) {
            return mSie[index];
        }

        public void setSie(String sie, int index) {
            mSie[index] = sie;
        }

        public String getCSie(int index) {
            return mCSie[index];
        }

        public void setCSie(String CSie, int index) {
            mCSie[index] = CSie;
        }
    }

