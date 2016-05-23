package data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2016/5/19.
 */
public class NewsData {



    private String title;
    private String description;
    private String picUrl;
    private String url;
    @SerializedName("ctime")
    private String cTime;

    //保存图片信息
    private Picture mPicture;


    public String getCtime() {
        return cTime;
    }

    public void setCTime(String cTime) {
        this.cTime = cTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUri() {
        return picUrl;
    }

    public void setPicUri(String picUri) {
        this.picUrl = picUri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Picture getPicture() {
        return mPicture;
    }

    public void setPicture(Picture picture) {
        mPicture = picture;
    }
}
