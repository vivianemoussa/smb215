package com.example.projetsmb215;
import com.example.projetsmb215.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.content.CursorLoader;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import static com.example.projetsmb215.DataProvider.CONTENT_URI_PROFILE;
import static com.example.projetsmb215.DataProvider.COL_ID;
import static com.example.projetsmb215.DataProvider.COL_NAME;
import static com.example.projetsmb215.DataProvider.COL_COUNT;


@SuppressLint("NewApi")
public class MessagesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	 
    private OnFragmentInteractionListener mListener;
    private SimpleCursorAdapter adapter;
     
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }   
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        adapter = new SimpleCursorAdapter(getActivity(), 
                R.layout.chat_list_item, 
                null, 
                new String[]{DataProvider.COL_MSG, DataProvider.COL_AT}, 
                new int[]{R.id.text1, R.id.text2},
                0);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
             
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                switch(view.getId()) {
                case R.id.text1:
                    LinearLayout root = (LinearLayout) view.getParent().getParent();
                    if (cursor.getString(cursor.getColumnIndex(DataProvider.COL_FROM)) == null) {
                        root.setGravity(Gravity.RIGHT);
                        root.setPadding(50, 10, 10, 10);
                    } else {
                        root.setGravity(Gravity.LEFT);
                        root.setPadding(10, 10, 50, 10);
                    }
                    break;                  
                }
                return false;
            }
        });
        setListAdapter(adapter);
    }   
 
    private void setListAdapter(SimpleCursorAdapter adapter2) {
		// TODO Auto-generated method stub
		
	}


	public void onDetach() {
        super.onDetach();
        mListener = null;
    }
 
    public interface OnFragmentInteractionListener {
        public String getProfileEmail();
    }
    
    


    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
         return new CursorLoader(getActivity(), 
            		CONTENT_URI_PROFILE,new String[]{COL_ID, COL_NAME, COL_COUNT},null,null,COL_ID + " DESC"); 
           
        }
    	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            adapter.swapCursor(data);
        }
    	public void onLoaderReset(Loader<Cursor> loader) {
            adapter.swapCursor(null);
        }
    }
               
    

                