package cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import data.GetPictureName;

/**
 * Created by admin on 2016/5/24.
 */
public class LocalCacheUtils {

    private static String mCache = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cache" + File.separator;

    public Bitmap getBitmapFromLocal(String url){

        String fileName = null;

        try {

            fileName = GetPictureName.getPictureName(url);

            String apkFile = mCache + fileName;
            File file = new File(apkFile);

            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void setBitmapToLocal(String url, Bitmap bitmap){

        try {

            String fileName = GetPictureName.getPictureName(url);

            File cache = new File(mCache);
            if(!cache.exists()){
                cache.mkdirs();
            }

            String apkFile = mCache + fileName;
            File file = new File(apkFile);

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);

            out.flush();
            out.close();

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
