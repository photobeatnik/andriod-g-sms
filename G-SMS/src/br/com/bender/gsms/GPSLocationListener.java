package br.com.bender.gsms;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

class GPSLocationListener implements LocationListener 
    {
    	private String longetitude;
    	private String latitude;
        @Override
        public void onLocationChanged(Location loc) {
        	longetitude = Double.toString((double) loc.getLongitude());
        	latitude = Double.toString((double) loc.getLatitude());
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

		@Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
        public String getLongetitude() {
			return longetitude;
		}

		public String getLatitude() {
			return latitude;
		}
}
