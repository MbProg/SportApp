package org.tud.bp.fitup.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.tud.bp.fitup.DataAccessLayer.DAL_Utilities;

public class ActivityKompass extends AppCompatActivity {
    private WebView webViewkompass;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.tud.bp.fitup.R.layout.actitivity_kompass);
        InitializeControlls();
        SetControlValues();
        super.onStart();
    }

    private void InitializeControlls() {
        webViewkompass = (WebView) findViewById(org.tud.bp.fitup.R.id.webview_kompass);
    }

    private void SetControlValues() {
        webViewkompass.getSettings().setJavaScriptEnabled(true);
        webViewkompass.loadUrl(DAL_Utilities.getKompassURL(this));
        webViewkompass.clearView();
        webViewkompass.measure(100, 100);
        webViewkompass.getSettings().setUseWideViewPort(true);
        webViewkompass.getSettings().setLoadWithOverviewMode(true);
        progressBar = ProgressDialog.show(this, getString(org.tud.bp.fitup.R.string.kompass), getString(org.tud.bp
                .fitup.R.string.wird_geladen));

        webViewkompass.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }


        });
    }

}
