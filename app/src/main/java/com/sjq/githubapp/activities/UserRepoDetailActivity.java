package com.sjq.githubapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjq.githubapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class UserRepoDetailActivity extends AppCompatActivity {
    private WebView mWebview;
    private ImageView back_img;
    private String url;
    private TextView title_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo_detail);
        url = getIntent().getExtras().getString("url");
        Log.i("url", url);
        initView();
    }

    public static void startToUseRepoDetailActivity(Context context, String url) {
        Intent intent = new Intent(context, UserRepoDetailActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public void doBack(View view) {
        onBackPressed();
    }

    public void initView() {
        mWebview = findViewById(R.id.web_view);
        back_img = findViewById(R.id.back_img);
        title_tv = findViewById(R.id.title_tv);
        if (url.isEmpty()) {
            return;
        }

        mWebview.loadUrl(url);


        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        WebChromeClient chromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                title_tv.setText(title);
            }
        };
        mWebview.setWebChromeClient(chromeClient);
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            this.finish();
        }
    }
}
