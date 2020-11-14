package com.dindin.hotrovndemo;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageChoose {
    private boolean isUri;
    private Uri uri;
    private Bitmap bitmap;

    public ImageChoose() {
    }

    public ImageChoose(boolean isUri, Uri uri, Bitmap bitmap) {
        this.isUri = isUri;
        this.uri = uri;
        this.bitmap = bitmap;
    }

    public boolean isUri() {
        return isUri;
    }

    public void setUri(boolean uri) {
        isUri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
