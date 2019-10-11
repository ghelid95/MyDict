package com.ghelidworks.android.mydictionarylite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;
import java.util.UUID;

public class NounPagerActivity extends AppCompatActivity {



    private static final String TAG = "usingApp";
    private static final String EXTRA_NOUN_ID ="com.ghelidworks.android.mydictionarylite.noun_id";

    private ViewPager mViewPager;
    private List<Noun> mNouns;



    public static Intent newIntent(Context packageContext, UUID nounId){
        Intent intent = new Intent(packageContext, NounPagerActivity.class);
        intent.putExtra(EXTRA_NOUN_ID,nounId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noun_pager);

        UUID nounId = (UUID) getIntent().getSerializableExtra(EXTRA_NOUN_ID);

        mViewPager = (ViewPager) findViewById(R.id.noun_view_pager);
        mNouns = NounLab.get(this).getNoun();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Noun noun = mNouns.get(position);
                return NounFragment.newInstance(noun.getId(),true);
            }

            @Override
            public int getCount() {
                return mNouns.size();
            }
        });
        for (int i = 0; i < mNouns.size(); i++){
            Log.d(TAG, "entered");
            if (mNouns.get(i).getId().equals(nounId)){
                Log.d(TAG, "used");
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
