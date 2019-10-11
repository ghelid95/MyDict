package com.ghelidworks.android.mydictionarylite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ListNounActivity extends SingleFragmentActivity {
    private static final String TAG = "usingApp";

    public static Intent newIntent(Context packageContext){
        Log.d(TAG, "Activity started");
        Intent intent = new Intent(packageContext, ListNounActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new ListNounFragment();
    }
}
