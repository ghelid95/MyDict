package com.ghelidworks.android.mydictionarylite;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ListNounFragment extends Fragment {

    private RecyclerView mNounRecyclerView;
    private NounAdapter mAdapter;
    private static final String TAG = "usingApp";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_noun_list, container, false);
        mNounRecyclerView = (RecyclerView) view.findViewById(R.id.noun_recycler_view);
        mNounRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        Log.d(TAG,"started");
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        NounLab nounLab = NounLab.get(getActivity());
        List<Noun> nouns = nounLab.getNoun();

        if (mAdapter == null){
            mAdapter = new NounAdapter(nouns);
            mNounRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setNouns(nouns);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_noun_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_noun:
                Noun noun = new Noun();
                NounLab.get(getActivity()).addNoun(noun);
                Intent intent = NewNounActivity
                        .newIntent(getActivity(), noun.getId());
                startActivity(intent);
                return true;
            case R.id.delete_noun:
                ((AppCompatActivity) getActivity()).startSupportActionMode(mActionModeCallback);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private MultiSelector mMultiSelector = new MultiSelector();
    private class NounHolder extends SwappingHolder implements View.OnClickListener  {

        private TextView mText;
        private TextView mType;
        private Noun mNoun;
        private TextView mTranslate;

        public NounHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_noun, parent, false),mMultiSelector);
            itemView.setOnClickListener(this);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = NounPagerActivity.newIntent(getActivity(),mNoun.getId());
                    startActivity(intent);
                    return true;
                }
            });


            mText = (TextView) itemView.findViewById(R.id.noun_text);
            mType = (TextView) itemView.findViewById(R.id.noun_type);
            mTranslate = (TextView) itemView.findViewById(R.id.translate);
        }
        public void bind(Noun noun){
            mNoun = noun;
            mText.setText(mNoun.getWord());
            mType.setText(mNoun.getTypeText());
            mTranslate.setText(mNoun.getTranslate());
        }
        @Override
        public void onClick(View view){
            if(!mMultiSelector.tapSelection(NounHolder.this)) {

            }
            mMultiSelector.setSelected(NounHolder.this, true);
        }

    }



    private ModalMultiSelectorCallback mActionModeCallback
            = new ModalMultiSelectorCallback(mMultiSelector) {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu){
            super.onCreateActionMode(actionMode, menu);
            getActivity().getMenuInflater().inflate(R.menu.list_context_menu, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.context_delete){
                actionMode.finish();

                for(int i = mAdapter.getItemCount(); i >=0; i--){
                    Log.d(TAG, String.valueOf(i));
                    if(mMultiSelector.isSelected(i,0)){
                        Log.d(TAG, "entered");
                        NounLab.get(getActivity()).delNoun(mAdapter.getNounId(i));
                        mNounRecyclerView.getAdapter().notifyItemRemoved(i);
                    }
                }

                mMultiSelector.clearSelections();
                updateUI();
                return true;
            }

            return false;
        }
    };

    private class NounAdapter extends RecyclerView.Adapter<NounHolder> {

        private List<Noun> mNouns;

        public NounAdapter(List<Noun> nouns) {
            mNouns = nouns;
        }

        @Override
        public NounHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new NounHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull NounHolder holder, int position) {
            Noun noun = mNouns.get(position);
            holder.bind(noun);

        }

        @Override
        public int getItemCount() {
            return mNouns.size();
        }

        public UUID getNounId(int elem) {
            Log.d(TAG, mNouns.get(elem).getId().toString());
            return mNouns.get(elem).getId();
        }

        public void setNouns(List<Noun> nouns) {
            mNouns = nouns;
        }
    }
}
