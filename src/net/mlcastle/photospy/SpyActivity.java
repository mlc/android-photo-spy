package net.mlcastle.photospy;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SpyActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {
    private static final String TAG = "SpyActivity";
    private static final String[] PROJECTION = {
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID
    };
    private ListView list;
    private View empty;
    private SimpleCursorAdapter adapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list = (ListView)findViewById(android.R.id.list);
        empty = findViewById(android.R.id.empty);
        
        list.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);

        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, PROJECTION, new int[] { android.R.id.text1, android.R.id.text2 }, 0);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, PROJECTION, null, null, null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
        Log.i(TAG, cursor.getCount() + " rows");
//        String[] names = cursor.getColumnNames();
//        int namelen = names.length;
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()) {
//            StringBuilder bld = new StringBuilder();
//            for (int j = 0; j < namelen; ++j) {
//                bld.append(names[j]).append("=\"").append(cursor.getString(j)).append("\" ");
//            }
//            Log.d(TAG, bld.toString());
//            cursor.moveToNext();
//        }
        list.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
        Intent intent = new Intent(this, ShowImage.class);
        intent.setData(uri);
        startActivity(intent);
    }
}
