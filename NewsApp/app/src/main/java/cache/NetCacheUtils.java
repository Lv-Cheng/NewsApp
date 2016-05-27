package cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.graphics.Bitmap.Config.ARGB_4444;

/**
 * Created by admin on 2016/5/24.
 */
public class NetCacheUtils {

    //用于将下载完成的bitmap保存进内存以及本地
    private LocalCacheUtils mLocalCacheUtils;
    private  MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils){
        this.mLocalCacheUtils = localCacheUtils;
        this.mMemoryCacheUtils = memoryCacheUtils;
    }


    public void getBitmapFromNet(ImageView imageView, String url){
        new BitmapTask().execute(imageView, url);
    }


    public class BitmapTask extends AsyncTask<Object, Void, Bitmap>{

        private ImageView mImageView;
        private String mUrl;

        public BitmapTask(){

        }


        @Override
        protected Bitmap doInBackground(Object... params) {

            this.mImageView = (ImageView) params[0];
            this.mUrl = (String) params[1];
            return downLoadBitmap(mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if(bitmap != null){

                mImageView.setImageBitmap(bitmap);

                mLocalCacheUtils.setBitmapToLocal(mUrl, bitmap);
                mMemoryCacheUtils.setBitmapToMemory(mUrl, bitmap);
            }

        }

        private Bitmap downLoadBitmap(String url){

            HttpURLConnection httpURLConnection = null;

            try {
                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod("GET");

                int requestCode = httpURLConnection.getResponseCode();

                if(requestCode == 200){

                    BitmapFactory.Options options= new BitmapFactory.Options();
                    options.inSampleSize = 20;
                    options.inPreferredConfig=Bitmap.Config.ARGB_4444;

                    return BitmapFactory.decodeStream(httpURLConnection.getInputStream(), null, options);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }

            return null;

        }
    }

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
