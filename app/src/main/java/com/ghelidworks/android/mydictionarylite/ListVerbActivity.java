package com.ghelidworks.android.mydictionarylite;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ListVerbActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ListVerbActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new ListVerbFragment();
    }
}
