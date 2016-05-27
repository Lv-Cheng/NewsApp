package cache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by admin on 2016/5/24.
 */
public class MemoryCacheUtils {

    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils(){

        long maxMemory = Runtime.getRuntime().maxMemory() / 8;

        mMemoryCache=new LruCache<String,Bitmap>((int) maxMemory){
            //用于计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }

    public Bitmap getBitmapFromMemory(String url){

        System.out.println("Geturl的值为: " +  url);
        System.out.println("容量为 + " + mMemoryCache.size());
        Bitmap bitmap = mMemoryCache.get(url);

        return bitmap;
    }

    public void setBitmapToMemory(String url, Bitmap bitmap){
        System.out.println("Seturl的值为: " +  url);
        mMemoryCache.put(url, bitmap);
        Log.i("setBitmapToMemory", "路过");
    }
}
