package net.mlcastle.photospy;

import java.io.InputStream;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

public class BitmapLoader extends AsyncTaskLoader<Bitmap> {
    private final Uri uri;
    private final int dimen;

    public BitmapLoader(Context ctx, Uri uri, int dimen) {
        super(ctx);
        this.uri = uri;
        this.dimen = dimen;
    }

    @Override
    public Bitmap loadInBackground() {
        final ContentResolver res = getContext().getContentResolver();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        read(res, uri, opts);

        int imgdimen = Math.max(opts.outHeight, opts.outWidth);
        opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;
        while (imgdimen > dimen) {
            imgdimen /= 2;
            opts.inSampleSize <<= 1;
        }
        opts.inSampleSize >>= 1;
        return read(res, uri, opts);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    private static Bitmap read(ContentResolver res, Uri uri, BitmapFactory.Options opts) {
        InputStream in = null;
        try {
            in = res.openInputStream(uri);
            return BitmapFactory.decodeStream(in, null, opts);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignore) { }
            }
        }
    }
}
