package com.xProject.XosoVIP.xoso.view.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.R;

public class WebViewActivity extends BasicActivity {

    Toolbar toolbar;
    WebView webView;
    String url = "";
    ActionBar actionBar;
    private boolean loadingFinished;
    private boolean redirect;
    String title;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đang tải");

        webView = (WebView) findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setDatabasePath("/data/data/" + webView.getContext().getPackageName() + "/databases/");
        }
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
//        WebView.setWebContentsDebuggingEnabled(true);

//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            try {
//                Log.d("WEBVIEW ", "Enabling HTML5-Features");
//                Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", Boolean.TYPE);
//                m1.invoke(webSettings, Boolean.TRUE);
//
//                Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", Boolean.TYPE);
//                m2.invoke(webSettings, Boolean.TRUE);
//
//                Method m3 = WebSettings.class.getMethod("setDatabasePath", String.class);
//                m3.invoke(webSettings, "/data/data/" + getPackageName() + "/databases/");
//
//                Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", Long.TYPE);
//                m4.invoke(webSettings, 1024*1024*8);
//
//                Method m5 = WebSettings.class.getMethod("setAppCachePath", String.class);
//                m5.invoke(webSettings, "/data/data/" + getPackageName() + "/cache/");
//
//                Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", Boolean.TYPE);
//                m6.invoke(webSettings, Boolean.TRUE);
//
//                Log.d("WEBVIEW ", "Enabled HTML5-Features");
//            }
//            catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//                Log.e("WEBVIEW ", "Reflection fail", e);
//            }
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        }
//        else {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        getData();
    }

    // Inject CSS method: read style.css from assets folder
// Append stylesheet to document head
//    private void injectCSS() {
//        try {
//            InputStream inputStream = getAssets().open("css.css");
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            String encoded = "body{width:"+( Utils.getWidth(getActivity())-100)+"px;}";// Base64.encodeToString(buffer, Base64.NO_WRAP);
//            webView.loadUrl("javascript:(function() {" +
//                    "var parent = document.getElementsByTagName('head').item(0);" +
//                    "var style = document.createElement('style');" +
//                    "style.type = 'text/css';" +
//                    // Tell the browser to BASE64-decode the string into your script !!!
//                    "style.innerHTML = window.atob('" + encoded + "');" +
//                    "parent.appendChild(style)" +
//                    "})()");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    private void getData() {
        url = getIntent().getStringExtra(Constants.URL);
        title = getIntent().getStringExtra(Constants.TITLE);
        actionBar.setTitle(title);

//        String myTemplate = "<a href=\"#link\">LINK!</a>";
        webView.loadUrl(url);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // only for KITKAT and newer versions
        }
//        else {
//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            CustomTabsIntent intent = builder.build();
//            intent.launchUrl(this, Uri.parse(url));
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

//    @SuppressLint("SetJavaScriptEnabled")
//    private class myWebClient extends WebViewClient {
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            // TODO Auto-generated method stub
//            loadingFinished = false;
//            showProgressBar(false, false, null, "Đang tải...");
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                webView.loadUrl(url);
////            }
//            if (!loadingFinished) {
//                redirect = true;
//            }
//            view.getSettings().setJavaScriptEnabled(true);
//            loadingFinished = false;
//            return false;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            if (!redirect) {
//                loadingFinished = true;
////                webView = view;
//                view.getSettings().setJavaScriptEnabled(true);
//            }
//            if (loadingFinished && !redirect) {
//                closeProgressBar();
//                if (view.getTitle()!=null){
//                    actionBar.setTitle(view.getTitle());
//                }
//            } else {
//                redirect = false;
//            }
//        }
//    }
}
