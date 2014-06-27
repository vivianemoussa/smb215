package com.example.projetsmb215;

import static com.example.projetsmb215.Constants.TAG;

import java.io.IOException;
import java.util.Random;
import static com.example.projetsmb215.ServerUtilities.register;
import static com.example.projetsmb215.GcmBroadcastReceiver.ctx;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class DemoActivity extends Activity {
	 private static final int MAX_ATTEMPTS = 5;
	    private static final int BACKOFF_MILLI_SECONDS = 2000;
	    private static final Random random = new Random();
	    GoogleCloudMessaging gcm;
	    
		AsyncTask<Void, Void, Boolean> mRegisterTak;
		
 public DemoActivity(Context applicationContext) {
			// TODO Auto-generated constructor stub
		}

protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity);
		        
		  mRegisterTak=new AsyncTask<Void, Void, Boolean>() {
          @Override
		protected Boolean doInBackground(Void... params) {
	        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);
	        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
	            Log.d(TAG, "Attempt #" + i + " to register");
	            try {
	                if (gcm == null) {
	                	gcm = GoogleCloudMessaging.getInstance(ctx);
	                }
	                String regid = gcm.register(Common.getSenderId());
	 
	  
	                // You should send the registration ID to your server over HTTP,
	                // so it can use GCM/HTTP or CCS to send messages to your app.
	                ServerUtilities.register(Common.getPreferredEmail(), regid);
	 
	                // Save the regid - no need to register again.
	                setRegistrationId(regid);
	                return Boolean.TRUE;
	                 
	            } catch (IOException ex) {
	                Log.e(TAG, "Failed to register on attempt " + i + ":" + ex);
	                if (i == MAX_ATTEMPTS) {
	                    break;
	                }
	                try {
	                    Log.d(TAG, "Sleeping for " + backoff + " ms before retry");
	                    Thread.sleep(backoff);
	                } catch (InterruptedException e1) {
	                    // Activity finished before we complete - exit.
	                    Log.d(TAG, "Thread interrupted: abort remaining retries!");
	                    Thread.currentThread().interrupt();
	                }
	                // increase backoff exponentially
	                backoff *= 2;                       
	            }
	        }
	        return Boolean.FALSE;
	    }
	 

	};mRegisterTak.execute(null, null, null);
	                

}

protected void setRegistrationId(String regid) {
	// TODO Auto-generated method stub
	
}

public void cleanup() {
	// TODO Auto-generated method stub
	
}}
