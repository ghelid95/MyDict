package com.ghelidworks.android.mydictionarylite;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NounTestFragment extends Fragment {

    private Button mMasculine;
    private Button mFeminine;
    private Button mNeuter;
    private Button mTranslate;
    private Button mPlural;
    private Button mNextNoun;
    private TextView mTestNoun;
    private TextView mTranslateNoun;
    private TestSession mTestSession;
    private int mIndex;
    private int mLastIndex;
    private List<Integer> mExcludeNums = new ArrayList<>();
    private static final String TAG = "usingApp";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_noun_test, container, false);
        mTestNoun = (TextView) view.findViewById(R.id.test_text_noun);
        mTranslateNoun = (TextView) view.findViewById(R.id.test_translate_text);
        mMasculine = (Button) view.findViewById(R.id.der_test_button);
        mMasculine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestSession.checkResult(0);
            }
        });
        mFeminine = (Button) view.findViewById(R.id.die_test_button);
        mFeminine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestSession.checkResult(2);
            }
        });
        mNeuter = (Button) view.findViewById(R.id.das_test_button);
        mNeuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestSession.checkResult(1);
            }
        });
        mTranslate = (Button) view.findViewById(R.id.test_translate_button);
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showText(false);
            }
        });
        mNextNoun = (Button) view.findViewById(R.id.next_test_noun_button);
        mNextNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestSession.generateNextNoun();
            }
        });
        mPlural = (Button) view.findViewById(R.id.test_plural_button);
        mPlural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showText(true);
            }
        });
        mTestSession = new TestSession();
        return view;
    }

    private class TestSession{
        private List<Noun> mNouns;
        public TestSession (){
            NounLab nounLab = NounLab.get(getActivity());
            mNouns = nounLab.getNoun();
            generateNextNoun();
        }

        public void generateNextNoun(){
            Log.d(TAG, "generate");
            mIndex = generateRandom();
            Log.d(TAG, String.valueOf(mIndex));
            mTestNoun.setText(mNouns.get(mIndex).getWord());
            mTranslateNoun.setText(mNouns.get(mIndex).getTranslate());
            mTranslateNoun.setVisibility(View.INVISIBLE);
            mNextNoun.setVisibility(View.INVISIBLE);
            mTestNoun.setTextColor(Color.BLACK);
        }

        public void checkResult(int answer){
            mNextNoun.setVisibility(View.VISIBLE);
            if(answer == mNouns.get(mIndex).getType()){
                mTestNoun.setText(mNouns.get(mIndex).getTypeText()+" "+mNouns.get(mIndex).getWord());
                mTestNoun.setTextColor(Color.GREEN);
                Toast toast = Toast.makeText(getActivity(),"Correct answer",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,300);
                toast.show();
            } else {
                mTestNoun.setText(mNouns.get(mIndex).getTypeText()+" "+mNouns.get(mIndex).getWord());
                mTestNoun.setTextColor(Color.RED);
                Toast toast = Toast.makeText(getActivity(),"Incorrect answer",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,300);
                toast.show();
            }
        }
        public int generateRandom() {
            Random rand = new Random();
            Log.d(TAG, "clear"+String.valueOf(mExcludeNums.size()));
            int random = rand.nextInt(mNouns.size());
            if (mExcludeNums.size() == mNouns.size()) {
                mExcludeNums.clear();
            }
            Log.d(TAG, String.valueOf(random));
            while (mExcludeNums.contains(random)|| random==mLastIndex) {
                random = rand.nextInt(mNouns.size());
            }
            mLastIndex = random;
            mExcludeNums.add(random);

            return random;
        }

    }
    public  void showText(boolean type){
        mTranslateNoun.setVisibility(View.VISIBLE);
        if(type){
            mTranslateNoun.setText(mTestSession.mNouns.get(mIndex).getPlural());
        } else{
            mTranslateNoun.setText(mTestSession.mNouns.get(mIndex).getTranslate());
        }

    }

}
