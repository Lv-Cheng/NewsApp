package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test.newsapp.activity.MainActivity;

/**
 * Created by admin on 2016/5/19.
 */
public class RequestDataTask extends AsyncTask<String, Integer, String> {

    private ArrayList<NewsData> mNewsArrayList;
    private InfoAdapter mInfoAdapter;
    private ListView mNewsListView;
    private Context mContext;
    ProgressDialog progressDialog = null;

    public  RequestDataTask(ArrayList<NewsData> newsArrayList, InfoAdapter infoAdapter,
                            ListView newsListView, Context context){
        this.mNewsArrayList = newsArrayList;
        this.mInfoAdapter = infoAdapter;
        this.mNewsListView = newsListView;
        this.mContext = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = ProgressDialog.show(mContext,
                "请稍等...", "数据正在加载中......", true);

    }

    @Override
    protected String doInBackground(String... params) {
        String httpUrl = "http://apis.baidu.com/txapi/world/world";
        String httpArg = "num=12&page=1";
        return RequestNewsData.request(httpUrl, httpArg);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressDialog.dismiss();
        JSONObject mJsonObject = null;
        try {
            mJsonObject = new JSONObject(result);
            //获得json里面的数组
            JSONArray jsonArray = mJsonObject.getJSONArray("newslist");
            mNewsArrayList.clear();
            mInfoAdapter.notifyDataSetChanged();
            NewsData[] newsDatas = new Gson().fromJson(jsonArray.toString(), NewsData[].class);

            for(int i = 0; i < newsDatas.length; i++){
                //将新闻数据添加进去
                newsDatas[i].setPicture(MainActivity.mPictures.get(i));
                mNewsArrayList.add(newsDatas[i]);
            }

            mInfoAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
