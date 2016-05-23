package data;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by admin on 2016/5/20.
 */
public class ImageTask extends AsyncTask<File, Void, Bitmap> {

    WeakReference<ImageView> mImageViewWeakReference = new WeakReference<>(null);
    ArrayList<NewsData> mNewsDatas;
    int mPosition;


    public ImageTask(ImageView imageView, ArrayList<NewsData> newsDatas, int position){
        mImageViewWeakReference = new WeakReference<>(imageView);
        this.mNewsDatas = newsDatas;
        this.mPosition = position;
    }

    @Override
    protected Bitmap doInBackground(File... params) {

        return BitMapUtils.zipBitMap(params[0].toString());
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mNewsDatas.get(mPosition).getPicture().setBitmap(bitmap);
        mImageViewWeakReference.get().setImageBitmap(bitmap);
    }
}
