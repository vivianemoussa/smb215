package com.example.projetsmb215;
import com.example.projetsmb215.R;
import java.io.IOException;



import android.content.CursorLoader;
import android.annotation.SuppressLint;
import android.app.LoaderManager;
import  com.example.projetsmb215.MessagesFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*public class MainActivity extends Activity {
	TextView mDisplay;
    AsyncTask<Void, Void, Void> mRegisterTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkNotNull(SERVER_URL, "http://192.168.0.64:8080/Gcmserver/");
        checkNotNull(SENDER_ID, "1086298744334");
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        setContentView(R.layout.main);
        mDisplay = (TextView) findViewById(R.id.display);
        registerReceiver(mHandleMessageReceiver,
                new IntentFilter(DISPLAY_MESSAGE_ACTION));
        final String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            GCMRegistrar.register(this, SENDER_ID);
        } else {
           
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                mDisplay.append(getString(R.string.already_registered) + "\n");
            } else {
               
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        boolean registered =
                                ServerUtilities.register(context, regId);
                       
                        if (!registered) {
                            GCMRegistrar.unregister(context);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
          
            case R.id.options_clear:
                mDisplay.setText(null);
                return true;
            case R.id.options_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        unregisterReceiver(mHandleMessageReceiver);
        GCMRegistrar.onDestroy(this);
        super.onDestroy();
    }

    private void checkNotNull(Object reference, String name) {
        if (reference == null) {
            throw new NullPointerException(
                    getString(R.string.error_config, name));
        }
    }

    private final BroadcastReceiver mHandleMessageReceiver =
            new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            mDisplay.append(newMessage + "\n");
        }
    };

}}*/
@SuppressLint("NewApi")
public abstract class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    
    private SimpleCursorAdapter adapter;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        adapter = new SimpleCursorAdapter(this, 
                R.layout.main_list_item, 
                null, 
                new String[]{DataProvider.COL_NAME, DataProvider.COL_COUNT}, 
                new int[]{R.id.text1, R.id.text2},
                0);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
             
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                switch(view.getId()) {
                case R.id.text2:
                    int count = cursor.getInt(columnIndex);
                    if (count > 0) {
                        ((TextView)view).setText(String.format("%d new message%s", count, count==1 ? "" : "s"));
                    }
                    return true;                    
                }
                return false;
            }
        });
        setListAdapter(adapter);
         
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        getLoaderManager().initLoader(0, null, this);
    }
     
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Common.PROFILE_ID, String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
     
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_add:
            AddContactDialog dialog = new AddContactDialog();
            dialog.show(getFragmentManager(), "AddContactDialog");
            return true;
             
        case R.id.action_settings:
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;            
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    public BroadcastReceiver getRegistrationStatusReceiver() {
		return registrationStatusReceiver;
	}


	private final BroadcastReceiver registrationStatusReceiver = new  BroadcastReceiver() {
    	 
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && Common.ACTION_REGISTER.equals(intent.getAction())) {
                switch (intent.getIntExtra(Common.EXTRA_STATUS, 100)) {
                case Common.STATUS_SUCCESS:
                    getActionBar().setSubtitle("online");
                    break;
                     
                case Common.STATUS_FAILED:
                    getActionBar().setSubtitle("offline");                  
                    break;                  
                }
            }
        }
    };


                    
}
                