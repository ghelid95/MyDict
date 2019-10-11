package com.ghelidworks.android.mydictionarylite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

public class NounFragment extends Fragment {
    private static final String ARG_NOUN_ID = "noun_id";
    private static final String ARG_UPDATE = "update";
    private static final String TAG = "usingApp";

    private Noun mNoun;
    private EditText mNounField;
    private RadioGroup mType;
    private EditText mTranslateField;
    private EditText mPluralForm;
    private Button mAddNoun;
    private boolean mUpdate;

    public static NounFragment newInstance(UUID nounID, boolean update){
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOUN_ID, nounID);
        args.putSerializable(ARG_UPDATE, update);
        NounFragment fragment = new NounFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID nounId = (UUID) getArguments().getSerializable(ARG_NOUN_ID);
        mUpdate = (boolean) getArguments().getSerializable(ARG_UPDATE);
        mNoun = NounLab.get(getActivity()).getNoun(nounId);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(TextUtils.isEmpty(mTranslateField.getText().toString())||TextUtils.isEmpty(mNounField.getText().toString())) {
            NounLab.get(getActivity()).delNoun(mNoun.getId());
        }
        NounLab.get(getActivity())
                .updateNoun(mNoun, mUpdate);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_noun, container, false);


        mNounField = (EditText) v.findViewById(R.id.text_noun);
        mNounField.setText(mNoun.getWord());
        mNounField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNoun.setWord(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mType = (RadioGroup) v.findViewById(R.id.type_group);
        ((RadioButton)mType.getChildAt(mNoun.getType())).setChecked(true);
        mType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case(R.id.type_radio_1):
                        Log.d(TAG, "radio 1");
                        mNoun.setType(0);
                        return;
                    case(R.id.type_radio_2):
                        Log.d(TAG, "radio 2");
                        mNoun.setType(1);
                        return;
                    case(R.id.type_radio_3):
                        Log.d(TAG, "radio 3");
                        mNoun.setType(2);
                        return;
                }

            }
        });
        mPluralForm = (EditText) v.findViewById(R.id.text_plural);
        mPluralForm.setText(mNoun.getPlural());
        mPluralForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNoun.setPlural(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTranslateField = (EditText) v.findViewById(R.id.text_translate);
        mTranslateField.setText(mNoun.getTranslate());
        mTranslateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNoun.setTranslate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAddNoun = (Button) v.findViewById(R.id.add_noun_button);
        mAddNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mTranslateField.getText().toString())||!TextUtils.isEmpty(mNounField.getText().toString())) {
                    NounLab.get(getActivity()).updateNoun(mNoun, mUpdate);
                    Noun noun = new Noun();
                    NounLab.get(getActivity()).addNoun(noun);
                    mNoun = noun;
                    mNounField.getText().clear();
                    mTranslateField.getText().clear();
                }
            }
        });
        if(mUpdate){
            mAddNoun.setVisibility(View.INVISIBLE);
        }


        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
    }
}

