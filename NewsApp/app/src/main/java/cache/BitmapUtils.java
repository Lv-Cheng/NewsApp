package cache;

import android.graphics.Bitmap;
import android.widget.ImageView;

import test.newsapp.R;

/**
 * Created by admin on 2016/5/25.
 */
public class BitmapUtils {

    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    private NetCacheUtils mNetCacheUtils;

    public BitmapUtils(){

        this.mLocalCacheUtils = new LocalCacheUtils();
        this.mMemoryCacheUtils = new MemoryCacheUtils();
        this.mNetCacheUtils = new NetCacheUtils(mMemoryCacheUtils, mLocalCacheUtils);
    }


    public void display(ImageView imageView, String url){

        imageView.setImageResource(R.mipmap.ic_launcher);

        Bitmap bitmap = null;


        //先加载内存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            return;
        }

        //内存没有，加载本地
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            mMemoryCacheUtils.setBitmapToMemory(url, bitmap);
            return;
        }

        //本地没有，下载
        mNetCacheUtils.getBitmapFromNet(imageView, url);

    }
}
