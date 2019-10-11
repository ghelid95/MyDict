package com.ghelidworks.android.mydictionarylite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import java.util.List;
import java.util.UUID;

public class ListVerbFragment extends Fragment {
    private RecyclerView mVerbRecyclerView;
    private ListVerbFragment.VerbAdapter mAdapter;
    private static final String TAG = "usingApp";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_noun_list, container, false);
        mVerbRecyclerView = (RecyclerView) view.findViewById(R.id.noun_recycler_view);
        mVerbRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        VerbLab verbLab = VerbLab.get(getActivity());
        List<Verb> verbs = verbLab.getVerb();

        if (mAdapter == null){
            mAdapter = new ListVerbFragment.VerbAdapter(verbs);
            mVerbRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setNouns(verbs);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_verb_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_verb:
                Verb verb = new Verb();
                VerbLab.get(getActivity()).addVerb(verb);
                Intent intent = NewVerbActivity
                        .newIntent(getActivity(), verb.getId());
                startActivity(intent);
                return true;
            case R.id.delete_verb:
                ((AppCompatActivity) getActivity()).startSupportActionMode(mActionModeCallback);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private MultiSelector mMultiSelector = new MultiSelector();
    private class VerbHolder extends SwappingHolder implements View.OnClickListener  {

        private TextView mText;
        private Verb mVerb;
        private TextView mTranslate;

        public VerbHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_verb, parent, false),mMultiSelector);
            itemView.setOnClickListener(this);
            itemView.setLongClickable(true);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = NounPagerActivity.newIntent(getActivity(),mVerb.getId());
                    startActivity(intent);
                    return true;
                }
            });


            mText = (TextView) itemView.findViewById(R.id.verb_text);
            mTranslate = (TextView) itemView.findViewById(R.id.translate_verb);
        }
        public void bind(Verb verb){
            mVerb = verb;
            mText.setText(mVerb.getWord());
            mTranslate.setText(mVerb.getTranslate());
        }
        @Override
        public void onClick(View view){
            if(!mMultiSelector.tapSelection(ListVerbFragment.VerbHolder.this)) {

            }
            mMultiSelector.setSelected(ListVerbFragment.VerbHolder.this, true);
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
                        NounLab.get(getActivity()).delNoun(mAdapter.getVerbId(i));
                        mVerbRecyclerView.getAdapter().notifyItemRemoved(i);
                    }
                }

                mMultiSelector.clearSelections();
                updateUI();
                return true;
            }

            return false;
        }
    };

    private class VerbAdapter extends RecyclerView.Adapter<VerbHolder> {

        private List<Verb> mVerbs;

        public VerbAdapter(List<Verb> verbs) {
            mVerbs = verbs;
        }

        @Override
        public VerbHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ListVerbFragment.VerbHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ListVerbFragment.VerbHolder holder, int position) {
            Verb verb = mVerbs.get(position);
            holder.bind(verb);

        }

        @Override
        public int getItemCount() {
            return mVerbs.size();
        }

        public UUID getVerbId(int elem) {
            Log.d(TAG, mVerbs.get(elem).getId().toString());
            return mVerbs.get(elem).getId();
        }

        public void setNouns(List<Verb> verbs) {
            mVerbs = verbs;
        }
    }
}
