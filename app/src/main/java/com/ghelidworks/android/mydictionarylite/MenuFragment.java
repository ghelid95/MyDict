package com.ghelidworks.android.mydictionarylite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    private Button mNounTest;
    private Button mAddNoun;
    private Button mAddVerb;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        mNounTest = (Button) view.findViewById(R.id.test);
        mNounTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NounTestActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        mAddNoun = (Button) view.findViewById(R.id.new_word);
        mAddNoun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ListNounActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        mAddVerb = view.findViewById(R.id.new_verb);
        mAddVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ListVerbActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        return view;
    }

}
