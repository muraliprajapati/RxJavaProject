package com.murali.rxjavaproject;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Murali on 09/06/2016.
 */

public class SearchActivity extends AppCompatActivity {
    public static final String ACTION = "ACTION_SEARCH";
    private static final String TAG = "SearchActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent: ");
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(TAG, "handleIntent: " + query);
            //use the query to search your data somehow
        }
    }

}
