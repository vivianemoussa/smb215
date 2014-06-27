package com.example.projetsmb215;
import com.example.projetsmb215.R;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import static com.example.projetsmb215.Constants.TAG;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

public class ServerUtilities {
	


	/**
	 * Issue a POST request to the server.
	 *
	 * @param endpoint POST address.
	 * @param params request parameters.
	 *
	 * @throws IOException propagated from POST.
	 */
	private static void post(String endpoint, Map<String, String> params) throws IOException {
	    URL url;
	    try {
	        url = new URL(endpoint);
	    } catch (MalformedURLException e) {
	        throw new IllegalArgumentException("invalid url: " + endpoint);
	    }
	    StringBuilder bodyBuilder = new StringBuilder();
	    Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
	    // constructs the POST body using the parameters
	    while (iterator.hasNext()) {
	        Entry<String, String> param = iterator.next();
	        bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
	        if (iterator.hasNext()) {
	            bodyBuilder.append('&');
	        }
	    }
	    String body = bodyBuilder.toString();
	     Log.v(TAG, "Posting '" + body + "' to " + url);
	    byte[] bytes = body.getBytes();
	    HttpURLConnection conn = null;
	    try {
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setFixedLengthStreamingMode(bytes.length);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	        // post the request
	        OutputStream out = conn.getOutputStream();
	        out.write(bytes);
	        out.close();
	        // handle the response
	        int status = conn.getResponseCode();
	        if (status != 200) {
	          throw new IOException("Post failed with error code " + status);
	        }
	    } finally {
	        if (conn != null) {
	            conn.disconnect();
	        }
	    }
	  }

	public static void register(Object preferredEmail, String regid) {
		// TODO Auto-generated method stub
		
	}

	public static void send(String txt, String profileEmail) {
		// TODO Auto-generated method stub
		
	}


}
