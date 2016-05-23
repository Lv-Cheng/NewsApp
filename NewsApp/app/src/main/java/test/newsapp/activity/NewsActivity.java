package test.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import test.newsapp.R;

/**
 * Created by admin on 2016/5/20.
 */
public class NewsActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.URL);



        mWebView = (WebView) findViewById(R.id.news_web_view);

        WebView.setWebContentsDebuggingEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new MyWebViewClient());

        mWebView.loadUrl(url);


    }

    @Override
    public void onBackPressed() {
        if(mWebView != null && mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }


    public class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
