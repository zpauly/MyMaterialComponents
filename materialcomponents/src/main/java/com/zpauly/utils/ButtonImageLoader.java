package com.zpauly.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by zpauly on 16-4-7.
 */
public class ButtonImageLoader {
    public static Bitmap decodeSampledBitmapFromRes(Resources res, int id, int height, int width) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        options.inSampleSize = getInSampledSize(options, height, width);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, id, options);
    }

    public static int getInSampledSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
        final int outHeight = options.outHeight;
        final int outWidth = options.outWidth;
        int inSampledSize = 1;

        if (outHeight > reqHeight || outWidth > reqWidth) {
            final int halfHeight = outHeight / 2;
            final int halfWidth = outWidth / 2;
            while (halfHeight / inSampledSize >= reqHeight
                    || halfWidth / inSampledSize >= reqWidth)
                inSampledSize *= 2;
        }

        return inSampledSize;
    }
}
