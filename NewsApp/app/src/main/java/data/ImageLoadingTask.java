package data;

import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by admin on 2016/5/21.
 */
public class ImageLoadingTask extends AsyncTask<String, Integer, String> {

    WeakReference<ImageView> mImageViewWeakReference = new WeakReference<>(null);
    ArrayList<NewsData> mNewsDatas;
    int mPosition;



    public ImageLoadingTask(ImageView imageView, ArrayList<NewsData> newsDatas, int position){
        mImageViewWeakReference = new WeakReference<>(imageView);
        this.mNewsDatas = newsDatas;
        this.mPosition = position;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(params[0]);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            final int contentLength = urlConnection.getContentLength();

            String downloadExternalPath = Environment.getExternalStorageDirectory() + File.separator + "cache" + File.separator;

            File fileExternalPath = new File(downloadExternalPath);
            if(!fileExternalPath.exists()){
                fileExternalPath.mkdir();
            }

            String[] s = params[0].split("/");
            int name = s.length - 1;
            String apkPath = downloadExternalPath + s[name];
            File apkFile = new File(apkPath);
            if(apkFile.exists()){
                apkFile.delete();
            }

            OutputStream outputStream = new FileOutputStream(apkFile);

            int length = 0;
            long downlaodSize = 0;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
                downlaodSize += length;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //下载完成就加载到内存中
        new ImageTask(mImageViewWeakReference.get(), mNewsDatas, mPosition).execute(mNewsDatas.get(mPosition).getPicture().getPictureFile());
    }
}