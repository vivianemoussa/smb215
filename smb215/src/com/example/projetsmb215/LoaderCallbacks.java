package com.example.projetsmb215;
import com.example.projetsmb215.R;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

public class LoaderCallbacks {
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this, 
                DataProvider.CONTENT_URI_PROFILE, 
                new String[]{DataProvider.COL_ID, DataProvider.COL_NAME, DataProvider.COL_COUNT}, 
                null, 
                null, 
                DataProvider.COL_ID + " DESC"); 
        return loader;
    }
     
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }
     
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
        


}
