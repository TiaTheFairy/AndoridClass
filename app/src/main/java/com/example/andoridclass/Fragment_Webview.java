package com.example.andoridclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

public class Fragment_Webview extends Fragment {

    private WebView webView;

    public Fragment_Webview(){

    }

    public static Fragment_Webview newInstance(){
        Fragment_Webview fragment = new Fragment_Webview();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment_webview, container, false);
        webView = rootView.findViewById(R.id.webview_web);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        WebSettings webSettings = webView.getSettings();
        webView.loadUrl("http://www.baidu.com");
        webSettings.setJavaScriptEnabled(true);
        return rootView;
    }
}