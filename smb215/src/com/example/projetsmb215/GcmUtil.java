package com.example.projetsmb215;
import com.example.projetsmb215.R;
import java.io.IOException;

import android.util.Log;

public class GcmUtil {
	new AsyncTask<Void, Void, Boolean>() {
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
	 
	    private void setRegistrationId(String regid) {
			// TODO Auto-generated method stub
			
		}

		protected void onPostExecute(Boolean status) {
	        //broadcastStatus(status);
	    }

		public void cleanup() {
			// TODO Auto-generated method stub
			
		}
	}.execute(null, null, null);
	                

}
