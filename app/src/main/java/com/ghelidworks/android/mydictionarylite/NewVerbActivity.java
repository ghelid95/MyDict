package com.ghelidworks.android.mydictionarylite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class NewVerbActivity extends SingleFragmentActivity {
    private static final String EXTRA_NOUN_ID ="com.ghelidworks.android.mydictionarylite.noun_id";

    public static Intent newIntent(Context packageContext, UUID nounId){
        Intent intent = new Intent(packageContext, NewVerbActivity.class);
        intent.putExtra(EXTRA_NOUN_ID,nounId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID verbId = (UUID) getIntent().getSerializableExtra(EXTRA_NOUN_ID);
        return VerbFragment.newInstance(verbId, false);
    }
}
