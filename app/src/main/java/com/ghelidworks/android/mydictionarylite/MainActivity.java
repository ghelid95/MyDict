package com.ghelidworks.android.mydictionarylite;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        return new MenuFragment();
    }



}
