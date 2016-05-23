package data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by admin on 2016/5/20.
 */
public class BitMapUtils {

    public static Bitmap zipBitMap(String filePath) {

        //不要先加载到内存中，获得图片的长宽
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = computSampleSize(options, 60, 60);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath,options);
    }

    //获得压缩的尺寸
    private static int computSampleSize(BitmapFactory.Options options, int w, int h) {

        int width = options.outWidth;
        int height = options.outHeight;
        //图片的缩小比例，只要小于等于，就是保持原图片的大小不变
        int inSqmpleSize = 1;
        if (width > w || height > h) {
            int zipSizeWidth = Math.round(width / w);
            int zipSizeHeight = Math.round(height / h);
            inSqmpleSize = zipSizeWidth < zipSizeHeight ? zipSizeWidth : zipSizeHeight;
        }
        return inSqmpleSize;
    }
}
