package data;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by admin on 2016/5/20.
 */
public class Picture {

    private File mPictureFile;
    private String mPictureName;
    private String mPictureurl;
    private Bitmap mBitmap = null;
    private boolean isDownloading = false;

    public Picture(){

    }

    public Picture(String url){
        this.mPictureurl = url;

        String[] s = url.split("/");
        int nameNumber = s.length - 1;
        this.mPictureName = s[nameNumber];

        this.mPictureFile = getPictureFile();
    }


    //获取文件
    public File getPictureFile() {

        String downloadExternalPath = Environment.getExternalStorageDirectory() + File.separator + "cache";
        File fileExternalPath = new File(downloadExternalPath);

        if(!fileExternalPath.exists()){
            fileExternalPath.mkdirs();
        }else {
        }

        String[] allFileString = fileExternalPath.list();
        if(Arrays.asList(allFileString).contains(this.mPictureName)){
            return new File(downloadExternalPath + File.separator + this.mPictureName);
        }else{
            return null;
        }
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getPictureurl() {
        return mPictureurl;
    }

    public void setPictureurl(String pictureurl) {
        mPictureurl = pictureurl;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void setIsDownloading(boolean isDownloading) {
        this.isDownloading = isDownloading;
    }
}
