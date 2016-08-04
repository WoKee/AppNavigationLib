/**
 * 
 */
package com.fph.appnavigationlib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 * com.fph.ConnectionChangeReceiver.java
 * 
 * Created by wang on 2016上午9:40:10 
 * 
 * Tips:
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {

//	Handler handler;
//
//	public ConnectionChangeReceiver(Handler handler){
//		this.handler=handler;
//	}


	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		 ConnectivityManager connectivityManager=(ConnectivityManager) arg0.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	        NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
	        	//TODO
	        }else{
	        	//TODO
            }


	}
	
}
