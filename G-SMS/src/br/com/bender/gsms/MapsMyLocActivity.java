package br.com.bender.gsms;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.MapView.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MapsMyLocActivity extends MapActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapsmyloc);
    	MapView mapa = (MapView) findViewById(R.id.mapView);
    	mapa.setBuiltInZoomControls(true);
    	
    	MyLocationOverlay mlo = new MyLocationOverlay(this, mapa) ;
    	//Desenha Compasso e minha licalização
    	mlo.enableCompass() ;
    	mlo.enableMyLocation() ;
    	mapa.getOverlays().add(mlo) ;
    	
//        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
//        View zoomView = mapa.getZoomControls(); 
// 
//        zoomLayout.addView(zoomView,
//            new LinearLayout.LayoutParams(
//                LayoutParams.WRAP_CONTENT, 
//                LayoutParams.WRAP_CONTENT)); 
//        mapa.displayZoomControls(true);
    }
    
    protected boolean isRouteDisplayed() {
    	return false;
    }
}


