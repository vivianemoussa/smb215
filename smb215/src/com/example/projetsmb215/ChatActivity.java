package com.example.projetsmb215;
import com.example.projetsmb215.R;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import  com.example.projetsmb215.DemoActivity;


/*@SuppressLint("NewApi")
public class ChatActivity extends Activity implements MessagesFragment.OnFragmentInteractionListener {
    private EditText msgEdit;
    private Button sendBtn;
    private String profileId, profileName, profileEmail;
    private DemoActivity gcmUtil;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity);
         
      //  profileId = getIntent().getStringExtra(CommonUtilities.PROFILE_ID);
        msgEdit = (EditText) findViewById(R.id.msg_edit);
        sendBtn = (Button) findViewById(R.id.send_btn);
         
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send(msgEdit.getText().toString());
                msgEdit.setText(null);
            }

        	private void send(final String txt) {
        	    new AsyncTask<Void, Void, String>() {
        	        @Override
        	        protected String doInBackground(Void... params) {
        	            String msg = "";
        	          //  ServerUtilities.send(txt, profileEmail);
						 
						ContentValues values = new ContentValues(2);
						values.put(DataProvider.COL_MSG, txt);
						values.put(DataProvider.COL_TO, profileEmail);
						getContentResolver().insert(DataProvider.CONTENT_URI_MESSAGES, values);
        	            return msg;
        	        }
        	 
        	        @Override
        	        protected void onPostExecute(String msg) {
        	            if (!TextUtils.isEmpty(msg)) {
        	                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        	            }
        	        }
        	    }.execute(null, null, null);        
        	}
        });
         
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
         
        Cursor c = getContentResolver().query(Uri.withAppendedPath(DataProvider.CONTENT_URI_PROFILE, profileId), null, null, null, null);
        if (c.moveToFirst()) {
            profileName = c.getString(c.getColumnIndex(DataProvider.COL_NAME));
            profileEmail = c.getString(c.getColumnIndex(DataProvider.COL_EMAIL));
            actionBar.setTitle(profileName);
        }
        actionBar.setSubtitle("connecting ...");
         
        BroadcastReceiver registrationStatusReceiver = null;
	//	registerReceiver(registrationStatusReceiver, new IntentFilter(CommonUtilities.ACTION_REGISTER));
      //  gcmUtil = new DemoActivity(getApplicationContext());
    }   
     
    @Override
    public String getProfileEmail() {
        return profileEmail;
    }   
 
    @Override
    protected void onPause() {
        //reset new messages count
        ContentValues values = new ContentValues(1);
        values.put(DataProvider.COL_COUNT, 0);
        getContentResolver().update(Uri.withAppendedPath(DataProvider.CONTENT_URI_PROFILE, profileId), values, null, null);
        super.onPause();
    }
 
    @Override
    protected void onDestroy() {
        BroadcastReceiver registrationStatusReceiver = null;
		unregisterReceiver(registrationStatusReceiver);
       // gcmUtil.cleanup();
        super.onDestroy();
    }
}*/
                