package test.newsapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cache.BitmapUtils;
import data.InfoAdapter;
import data.NewsData;
import data.Picture;
import data.RequestDataTask;
import test.newsapp.R;

public class MainActivity extends AppCompatActivity {


    public static final String URL = "URL";
    private ListView mNewListView;
    private ArrayList<NewsData> mNewsArrayList = new ArrayList<>();
    private InfoAdapter mInfoAdapter;
    private String[] picturePath;
    public static ArrayList<Picture> mPictures = new ArrayList<>();
    public static BitmapUtils bitmapUtils = new BitmapUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化一些变量
        initValue();

        //加载数据
        initArrayList();

        //设置点击事件
        setOnClick();

    }

    private void setOnClick() {
        mNewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入新闻网页
                goIntoWebView(position);
            }
        });
    }

    private void goIntoWebView(int position) {
        String url = mNewsArrayList.get(position).getUrl();
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        intent.putExtra(URL, url);
        startActivity(intent);
    }

    private void initArrayList() {
        //判断是否有网络连接
        if(NetWorkConnect.isNetWorkConnect(this)) {
            new RequestDataTask(mNewsArrayList, mInfoAdapter, mNewListView, this).execute();
        }else {
            Toast.makeText(MainActivity.this, "无网络连接", Toast.LENGTH_SHORT).show();
        }
    }

    private void initValue() {

        //获取数组xml文件中图片url
        picturePath = this.getResources().getStringArray(R.array.picture_path);
        for(int i = 0; i < picturePath.length; i++){
            mPictures.add(new Picture(picturePath[i]));
        }

        mNewListView = (ListView) findViewById(R.id.new_list_view);
        mInfoAdapter = new InfoAdapter(this, mNewsArrayList);
        mNewListView.setAdapter(mInfoAdapter);
    }


    //添加ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    //为ActionBar添加点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.refresh:
                initArrayList();
                break;
        }

        return true;
    }


}
