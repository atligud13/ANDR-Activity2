package com.example.atli.calculator;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Atli Guðlaugsson on 28/08/2015.
 */
public class CalcPreferenceActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource(R.xml.preferences);
    }
}
