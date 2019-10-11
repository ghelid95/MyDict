package com.ghelidworks.android.mydictionarylite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class NounTestActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, NounTestActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new NounTestFragment();
    }
}
