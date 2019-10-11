package com.ghelidworks.android.mydictionarylite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.UUID;

public class VerbFragment extends Fragment {
    private static final String ARG_VERB_ID = "verb_id";
    private static final String ARG_UPDATE = "update";
    private static final String TAG = "usingApp";

    private Verb mVerb;
    private EditText mVerbField;
    private CheckBox mType;
    private EditText mTranslateField;
    private EditText mIch;
    private EditText mDu;
    private EditText mErSieEs;
    private EditText mWir;
    private EditText mIhr;
    private EditText mSie;
    private EditText mCSie;
    private Button mAddVerb;
    private boolean mUpdate;
    private int mIndex = 0;

    public static VerbFragment newInstance(UUID verbID, boolean update){
        Bundle args = new Bundle();
        args.putSerializable(ARG_VERB_ID, verbID);
        args.putSerializable(ARG_UPDATE, update);
        VerbFragment fragment = new VerbFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID verbID = (UUID) getArguments().getSerializable(ARG_VERB_ID);
        mUpdate = (boolean) getArguments().getSerializable(ARG_UPDATE);
        mVerb = VerbLab.get(getActivity()).getVerb(verbID);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(TextUtils.isEmpty(mTranslateField.getText().toString())||TextUtils.isEmpty(mVerbField.getText().toString())) {
            VerbLab.get(getActivity()).delVerb(mVerb.getId());
        }
        VerbLab.get(getActivity())
                .updateVerb(mVerb, mUpdate);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_verb, container, false);


        mVerbField = (EditText) v.findViewById(R.id.text_verb);
        mVerbField.setText(mVerb.getWord());
        mVerbField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setWord(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mType = (CheckBox) v.findViewById(R.id.check_form);
        mType.setChecked(mVerb.getType());
        mType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mVerb.getType()){
                    mVerb.setType(0);
                }else{
                    mVerb.setType(1);
                }
            }
        });

        mTranslateField = (EditText) v.findViewById(R.id.text_verb_translate);
        mTranslateField.setText(mVerb.getTranslate());
        mTranslateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setTranslate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIch = v.findViewById(R.id.text_ich);
        mIch.setText(mVerb.getIch(mIndex));
        mIch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setIch(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDu = v.findViewById(R.id.text_du);
        mDu.setText(mVerb.getDu(mIndex));
        mDu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setDu(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mErSieEs = v.findViewById(R.id.text_er_sie_es);
        mErSieEs.setText(mVerb.getErSieEs(mIndex));
        mErSieEs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setErSieEs(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mWir = v.findViewById(R.id.text_wir);
        mWir.setText(mVerb.getWir(mIndex));
        mWir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setWir(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mIhr = v.findViewById(R.id.text_ihr);
        mIhr.setText(mVerb.getIhr(mIndex));
        mIhr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setIhr(s.toString(), mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSie = v.findViewById(R.id.text_sie);
        mSie.setText(mVerb.getSie(mIndex));
        mSie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setSie(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCSie = v.findViewById(R.id.text_Sie);
        mCSie.setText(mVerb.getCSie(mIndex));
        mCSie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mVerb.setCSie(s.toString(),mIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAddVerb = (Button) v.findViewById(R.id.add_verb_button);
        mAddVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mTranslateField.getText().toString())||!TextUtils.isEmpty(mVerbField.getText().toString())) {
                    VerbLab.get(getActivity()).updateVerb(mVerb, mUpdate);
                    Verb verb = new Verb();
                    VerbLab.get(getActivity()).addVerb(verb);
                    mVerb = verb;
                    clearAll();
                }
            }
        });
        if(mUpdate){
            mAddVerb.setVisibility(View.INVISIBLE);
        }


        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
    }
    public void clearAll(){
        mVerbField.getText().clear();
        mTranslateField.getText().clear();
        mIch.getText().clear();
        mDu.getText().clear();
        mErSieEs.getText().clear();
        mWir.getText().clear();
        mIhr.getText().clear();
        mSie.getText().clear();
        mCSie.getText().clear();
        mType.setChecked(false);
    }
}
