package br.com.bender.gsms;

import com.google.android.maps.MyLocationOverlay;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class GSMSTabWidget extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, MessageActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("message").setIndicator(null,res.getDrawable(R.drawable.ic_tab_artists)).setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, MapsMyLocActivity.class);
        spec = tabHost.newTabSpec("mapsmyloc").setIndicator(null,res.getDrawable(R.drawable.ic_tab_albums)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, HistoryActivity.class);
        spec = tabHost.newTabSpec("history").setIndicator(null,res.getDrawable(R.drawable.ic_tab_songs)).setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}