package net.mlcastle.photospy;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ShowImage extends FragmentActivity implements LoaderManager.LoaderCallbacks<Bitmap> {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv = new ImageView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(iv);

        Bundle args = new Bundle();
        args.putParcelable("uri", getIntent().getData());
        getSupportLoaderManager().initLoader(1, args, this);
    }

    public Loader<Bitmap> onCreateLoader(int i, Bundle args) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new BitmapLoader(this, (Uri)args.getParcelable("uri"), Math.max(metrics.heightPixels, metrics.widthPixels));
    }

    public void onLoadFinished(Loader<Bitmap> loader, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    public void onLoaderReset(Loader<Bitmap> loader) {
        iv.setImageBitmap(null);
    }
}
