package com.example.projetsmb215;
import com.example.projetsmb215.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

public class MessagesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	 
    private OnFragmentInteractionListener mListener;
    private SimpleCursorAdapter adapter;
     
    @Override
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

	private Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         
        Bundle args = new Bundle();
        args.putString(DataProvider.COL_EMAIL, mListener.getProfileEmail());
        getLoaderManager().initLoader(0, args, this);
    }
 
    private Object getLoaderManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void onDetach() {
        super.onDetach();
        mListener = null;
    }
 
    public interface OnFragmentInteractionListener {
        public String getProfileEmail();
    }
    
}
                